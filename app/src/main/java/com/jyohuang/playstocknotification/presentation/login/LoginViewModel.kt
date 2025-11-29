package com.jyohuang.playstocknotification.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

data class LoginUiState(
    val email : String = "",
    val password : String = "",
    val isLoading : Boolean = false,
    val errorMessage : String? = null,
    val infoMessage : String? = null
)

class LoginViewModel : ViewModel(){
    val auth : FirebaseAuth = FirebaseAuth.getInstance()

    var uiState by mutableStateOf(LoginUiState())
        private  set

    fun onEmailChanged(newEmail : String){
        uiState = uiState.copy(email = newEmail)
    }

    fun onPasswordChanged(newPassword : String){
        uiState = uiState.copy(password = newPassword)
    }

    private fun validateInput() : Boolean{
        val email = uiState.email.trim() //create@gmail.com
        val password = uiState.password

        if(email.isBlank() || password.isBlank()){
            uiState = uiState.copy(
                errorMessage = "請填寫帳號跟密碼"
            )
            return false
        }
        if(password.length < 6 ){
            uiState = uiState.copy(
                errorMessage = "密碼至少要6碼"
            )
            return false
        }

        return true

    }

    fun login(onSuccess : () -> Unit){
        if(!validateInput()){
            return
        }

        uiState = uiState.copy(isLoading = true , errorMessage = null)

        auth.signInWithEmailAndPassword(uiState.email, uiState.password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    uiState = LoginUiState()
                    onSuccess()
                }else{
                    val msg= mapAuthErrorMessage(task?.exception)
                    uiState = uiState.copy(
                        isLoading = false,
                        errorMessage = msg
                    )
                }
            }
    }
    fun register(onSuccess: () -> Unit){
        if(!validateInput()){
            return
        }

        auth.createUserWithEmailAndPassword(uiState.email, uiState.password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    uiState = LoginUiState()
                    onSuccess()
                }else{
                    val msg= mapAuthErrorMessage(task?.exception)
                    uiState = uiState.copy(
                        isLoading = false,
                        errorMessage = msg
                    )
                }
            }
    }
    private fun mapAuthErrorMessage(e: Exception?) : String{
        val raw = e?.message ?: return "發生錯誤"
        return when{
            raw.contains("email address is badly formatted", ignoreCase = true)->{
                "Email 格式不符合, 請再試一次"
            }
            raw.contains("password is invalid", ignoreCase = true) -> {
                "密碼錯誤,請再次一次"
            }
            raw.contains("address is already in use", ignoreCase = true) ->{
                "這個Email已經被註冊過, 可以直接登入"
            }
            else -> raw
        }
    }

    fun resetPassword(){
        val email = uiState.email.trim()

        if(email.isBlank()){
            uiState = uiState.copy(
                errorMessage = "請先輸入你的電子信箱",
                infoMessage = null
            )
            return
        }

        uiState = uiState.copy(
            isLoading = true,
            errorMessage = null,
            infoMessage = null
        )

        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    uiState = uiState.copy(
                        isLoading =  false,
                        errorMessage = null,
                        infoMessage = "重設密碼的信件已經寄出"
                    )
                }else{
                    val msg = task.exception?.localizedMessage ?: "寄信失敗"
                    uiState = uiState.copy(
                        isLoading = false,
                        errorMessage = msg,
                        infoMessage = null
                    )
                }

            }
    }

}
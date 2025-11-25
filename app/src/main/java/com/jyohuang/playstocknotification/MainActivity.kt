package com.jyohuang.playstocknotification

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.jyohuang.playstocknotification.presentation.login.LoginScreen
import com.jyohuang.playstocknotification.presentation.main.MainTabScaffold
import com.jyohuang.playstocknotification.ui.theme.PlaystocknotificationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaystocknotificationTheme {
                AppRoot()
            }
        }
    }
}

/**
 * AppRoot : 透過判斷是否已經登入了 來決定要顯示 登入頁還是主畫面
 */
@Composable
fun AppRoot(){
    var isLoggedIn by rememberSaveable { mutableStateOf(false) }

    if(!isLoggedIn){
        LoginScreen(
            onLoginSuccess = {
                //先不做驗證
                isLoggedIn = true
            }
        )
    }else{
        MainTabScaffold()
    }
}





/*
定義四個tab
 */
enum class BottomTab(
    val title : String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
){
    Home("首頁", Icons.Filled.Home),
    Search("股票查詢", Icons.Filled.Search),
    Favorite("我的最愛", Icons.Filled.Favorite),
    Notification("通知列表", Icons.Filled.List),
    Profile("個人頁面", Icons.Filled.Face)
}


@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    PlaystocknotificationTheme {
        LoginScreen(
            onLoginSuccess = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainPagePreview() {
    PlaystocknotificationTheme {
        MainTabScaffold(

        )
    }
}
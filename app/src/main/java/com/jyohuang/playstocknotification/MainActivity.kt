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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
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


/**
 * 登入頁面
 */
@Composable
fun LoginScreen(
    onLoginSuccess : () -> Unit
){
    var account by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Surface (
        modifier = Modifier.fillMaxSize()
    ){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "股票價格通知系統",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold

            )
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = account,
                onValueChange = { account = it},
                label = {Text("帳號")},
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {password = it},
                label = {Text("密碼")},
                visualTransformation = PasswordVisualTransformation(),
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    onLoginSuccess()
                }
            ) {
                Text("登入")
            }
        }
    }
}


@Composable
fun MainTabScaffold(){
    var currentTab by rememberSaveable{ mutableStateOf(BottomTab.Home) }
    Scaffold(
        bottomBar = {
            NavigationBar {
                BottomTab.values().forEach { tab ->
                    NavigationRailItem(
                        selected = currentTab == tab,
                        onClick = { currentTab = tab },
                        icon = {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = tab.title
                            )
                        },
                        label = {Text(tab.title)}
                    )

                }

            }
        }
    ){ innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ){
            when(currentTab){
                BottomTab.Home -> HomeScreen()
                BottomTab.Search -> StockSearchScreen()
                BottomTab.Favorite ->  FavoriteScreen()
                BottomTab.Notification -> NotificationListScreen()
                BottomTab.Profile -> ProfileListScreen()
            }
        }
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

@Composable
fun HomeScreen(){
    CenterTitle(title = "首頁 - 今日股市最新消息")
}

@Composable
fun StockSearchScreen(){
    CenterTitle(title = "股票查詢 - 依照名稱/代號 查詢 可以加入最愛")
}

@Composable
fun FavoriteScreen(){
    CenterTitle(title = "我的最愛 - 設定通知機制和觀看股票")
}

@Composable
fun NotificationListScreen(){
    CenterTitle(title = "通知列表 ex: xxx在 2026/1/31 10:05 達到 xx 元")
}
@Composable
fun ProfileListScreen(){
    CenterTitle(title = "個人頁面")
}


@Composable
fun CenterTitle(title : String){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = title,
            fontSize = 18.sp
        )
    }
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
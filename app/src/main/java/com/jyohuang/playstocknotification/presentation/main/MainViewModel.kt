package com.jyohuang.playstocknotification.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class MainViewModel : ViewModel() {
    var currentTab by mutableStateOf(BottomTab.Home)
        private set

    fun selectTab(tab : BottomTab){
        currentTab = tab
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
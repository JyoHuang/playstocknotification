package com.jyohuang.playstocknotification.presentation.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jyohuang.playstocknotification.BottomTab

class MainViewModel : ViewModel() {
    var currentTab by mutableStateOf(BottomTab.Home)

    fun selectTab(tab : BottomTab){
        currentTab = tab
    }
}
package com.jyohuang.playstocknotification.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jyohuang.playstocknotification.BottomTab
import androidx.lifecycle.viewmodel.compose.viewModel

import com.jyohuang.playstocknotification.presentation.favorite.FavoriteScreen
import com.jyohuang.playstocknotification.presentation.home.HomeScreen
import com.jyohuang.playstocknotification.presentation.notification.NotificationListScreen
import com.jyohuang.playstocknotification.presentation.profile.ProfileListScreen
import com.jyohuang.playstocknotification.presentation.search.StockSearchScreen


@Composable
fun MainTabScaffold(
    viewModel: MainViewModel = viewModel()
){
    var currentTab = viewModel.currentTab
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
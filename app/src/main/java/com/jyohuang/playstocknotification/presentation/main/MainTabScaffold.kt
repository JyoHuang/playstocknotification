package com.jyohuang.playstocknotification.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    val currentTab = viewModel.currentTab
    Scaffold(
        bottomBar = {
            NavigationBar {
                /*
                BottomTab.values().forEach { tab ->
                    NavigationRailItem(
                        selected = currentTab == tab,
                        onClick = { viewModel.selectTab(tab) },
                        icon = {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = tab.title
                            )
                        },
                        label = {Text(tab.title)}
                    )

                }

                 */
                NavigationBarItem(
                    selected = currentTab == BottomTab.Home,
                    onClick = { viewModel.selectTab(BottomTab.Home)},
                    icon = {Icon(Icons.Default.Home, contentDescription = "首頁")},
                    label = { Text("首頁")}
                )
                NavigationBarItem(
                    selected = currentTab == BottomTab.Search,
                    onClick = { viewModel.selectTab(BottomTab.Search)},
                    icon = {Icon(Icons.Default.Search, contentDescription = "股票查詢")},
                    label = { Text("股票查詢")}
                )
                NavigationBarItem(
                    selected = currentTab == BottomTab.Favorite,
                    onClick = { viewModel.selectTab(BottomTab.Favorite)},
                    icon = {Icon(Icons.Default.Favorite, contentDescription = "我的最愛")},
                    label = { Text("我的最愛")}
                )
                NavigationBarItem(
                    selected = currentTab == BottomTab.Notification,
                    onClick = { viewModel.selectTab(BottomTab.Notification)},
                    icon = {Icon(Icons.Default.List, contentDescription = "通知")},
                    label = { Text("通知")}
                )
                NavigationBarItem(
                    selected = currentTab == BottomTab.Profile,
                    onClick = { viewModel.selectTab(BottomTab.Profile)},
                    icon = {Icon(Icons.Default.Person, contentDescription = "個人頁面")},
                    label = { Text("個人頁面")}
                )
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
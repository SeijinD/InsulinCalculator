package com.seijind.insulincalculator.ui.home.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import com.seijind.insulincalculator.ui.home.composable.HomeScreen
import com.seijind.insulincalculator.ui.home.viewmodel.HomeViewModel
import com.seijind.insulincalculator.ui.navigation.Screen
import com.seijind.insulincalculator.ui.navigation.baseComposable

fun NavGraphBuilder.homeScreen() {
    baseComposable(
        route = Screen.Home.route
    ) {
        val viewModel: HomeViewModel = hiltViewModel()

        LaunchedEffect(key1 = Unit) {
            viewModel.init()
        }

        HomeScreen()
    }
}
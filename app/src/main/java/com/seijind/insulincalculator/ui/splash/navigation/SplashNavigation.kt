package com.seijind.insulincalculator.ui.splash.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.seijind.insulincalculator.ui.splash.composable.SplashScreen
import com.seijind.insulincalculator.ui.splash.viewmodel.SplashViewModel

internal const val SplashRoute = "splash"
internal typealias GoHome = () -> Unit

fun NavGraphBuilder.splashScreen(
    goHome: GoHome
) {
    composable(SplashRoute) {
        val viewModel: SplashViewModel = hiltViewModel()

        LaunchedEffect(key1 = Unit) {
            viewModel.init()
        }

        SplashScreen(goHome = { goHome() })
    }
}
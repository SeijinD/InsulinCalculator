package com.seijind.insulincalculator.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.seijind.insulincalculator.ui.InsulinCalculatorViewModel
import com.seijind.insulincalculator.ui.home.navigation.homeScreen
import com.seijind.insulincalculator.ui.splash.navigation.splashScreen
import com.seijind.insulincalculator.ui.theme.InsulinCalculatorTheme

@Composable
fun InsulinCalculatorNavHost(
    viewModel: InsulinCalculatorViewModel
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.init()
    }

    val navController = rememberNavController()

    InsulinCalculatorTheme {
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route
        ) {
            splashScreen(goHome = { navController.navigate(Screen.Home.route) })
            homeScreen()
        }
    }
}
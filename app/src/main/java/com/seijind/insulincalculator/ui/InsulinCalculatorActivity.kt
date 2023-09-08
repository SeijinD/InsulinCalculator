package com.seijind.insulincalculator.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.seijind.insulincalculator.ui.navigation.InsulinCalculatorNavHost
import com.seijind.insulincalculator.ui.theme.InsulinCalculatorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InsulinCalculatorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: InsulinCalculatorViewModel = hiltViewModel()
            InsulinCalculatorTheme {
                InsulinCalculatorNavHost(viewModel)
            }
        }
    }
}
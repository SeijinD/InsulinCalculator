package com.seijind.insulincalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.seijind.insulincalculator.ui.theme.InsulinCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InsulinCalculatorTheme {

            }
        }
    }
}
package com.seijind.insulincalculator.ui.home.model

import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf

object Foods {
    val foods = listOf(
        Food(
            name = mutableStateOf("Μακαρονια Ολικης Misko"),
            carbs = mutableDoubleStateOf(0.64)
        ),
        Food(
            name = mutableStateOf("Πατατες Φουρνου"),
            carbs = mutableDoubleStateOf(0.215)
        ),
        Food(
            name = mutableStateOf("Πατατες Τηγανιτες"),
            carbs = mutableDoubleStateOf(0.375)
        ),
        Food(
            name = mutableStateOf("Ψωμι Λευκο"),
            carbs = mutableDoubleStateOf(0.47)
        ),
        Food(
            name = mutableStateOf("Ψωμι Ολικης"),
            carbs = mutableDoubleStateOf(0.44)
        ),
        Food(
            name = mutableStateOf("Ψωμι Πολυσπορο"),
            carbs = mutableDoubleStateOf(0.44)
        ),
        Food(
            name = mutableStateOf("Γαλα"),
            carbs = mutableDoubleStateOf(0.06)
        ),
        Food(
            name = mutableStateOf(""),
            carbs = mutableDoubleStateOf(0.0)
        ),
    )
}
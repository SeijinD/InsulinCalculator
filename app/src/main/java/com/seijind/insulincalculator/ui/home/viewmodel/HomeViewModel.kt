package com.seijind.insulincalculator.ui.home.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.seijind.insulincalculator.ui.base.BaseViewModel
import com.seijind.insulincalculator.ui.home.model.Food
import com.seijind.insulincalculator.ui.home.model.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

): BaseViewModel() {

    var uiState: MutableState<HomeUiState?> = mutableStateOf(HomeUiState())
        private set

    fun init() {
        launch {
            uiState.value = HomeUiState(
                foods = mutableListOf(
                    Food(
                        name = "Apple",
                        carbs = 0.5,
                        grams = mutableIntStateOf(200)
                    ),
                    Food(
                        name = "Watermelon",
                        carbs = 0.3,
                        grams = mutableIntStateOf(300)
                    )
                ),
                divider = mutableIntStateOf(10),
                totalCarbs = mutableDoubleStateOf(50.0),
                gi = mutableIntStateOf(160),
                result = mutableStateOf("")
            )
        }
    }

}
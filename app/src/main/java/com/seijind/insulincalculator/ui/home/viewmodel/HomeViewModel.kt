package com.seijind.insulincalculator.ui.home.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.seijind.insulincalculator.ui.base.BaseViewModel
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
                result = mutableStateOf("5")
            )
        }
    }

}
package com.seijind.insulincalculator.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seijind.insulincalculator.util.LoadingLiveData
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun launch(function: suspend () -> Unit) {
        viewModelScope.launch {
            function.invoke()
        }
    }

    protected fun launchWithProgress(
        preload: suspend () -> Unit = {},
        postload: () -> Unit = {},
        function: suspend () -> Unit
    ) {
        viewModelScope.launch {
            preload.invoke()
            LoadingLiveData.postValue(true)
            function.invoke()
        }.apply {
            invokeOnCompletion {
                postload.invoke()
                LoadingLiveData.postValue(false)
            }
        }
    }
}
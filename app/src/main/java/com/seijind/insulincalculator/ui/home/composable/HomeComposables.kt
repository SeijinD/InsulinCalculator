package com.seijind.insulincalculator.ui.home.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.seijind.insulincalculator.ui.home.model.HomeUiState
import com.seijind.insulincalculator.ui.theme.InsulinCalculatorTheme
import com.seijind.insulincalculator.ui.theme.spacing

private typealias OnResultClicked = () -> Unit

@Composable
internal fun HomeScreen(
    uiState: HomeUiState?,
    onResultClicked: OnResultClicked
) {
    uiState?.let { state ->
        HomeContent(
            state = state,
            onResultClicked = { onResultClicked() }
        )
    }
}

@Composable
private fun HomeContent(
    state: HomeUiState,
    onResultClicked: OnResultClicked
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(all = MaterialTheme.spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
    ) {
        Text(text = "Home")
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeContentPreview() {
    InsulinCalculatorTheme {
        HomeContent(
            state = HomeUiState(),
            onResultClicked = {}
        )
    }
}
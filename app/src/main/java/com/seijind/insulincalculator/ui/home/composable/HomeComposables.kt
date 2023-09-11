package com.seijind.insulincalculator.ui.home.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.seijind.insulincalculator.R
import com.seijind.insulincalculator.ui.composables.CustomOutlinedTextField
import com.seijind.insulincalculator.ui.home.model.Food
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
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
    ) {
        val food by remember { mutableStateOf(Food()) }
        var divider by remember { mutableStateOf(state.divider.value) }
        var gi by remember { mutableStateOf(state.gi.value) }

        Text(text = "Home")
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.label_foods),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        CustomOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            text = food.grams.value,
            label = R.string.label_food_grams,
            placeholder = R.string.placeholder_food_grams,
            keyboardType = KeyboardType.Number,
            onTextChanged = { food.grams.value  = it },
            onClearClick = { food.grams.value = "" }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Green, shape = RoundedCornerShape(MaterialTheme.spacing.small))
                .clip(RoundedCornerShape(MaterialTheme.spacing.small))
                .clickable {  },
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = MaterialTheme.spacing.small),
                text = stringResource(id = R.string.button_add),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
        CustomOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            text = divider,
            label = R.string.label_divider,
            placeholder = R.string.placeholder_divider,
            keyboardType = KeyboardType.Number,
            onTextChanged = {
                divider = it
                state.divider.value = divider
            },
            onClearClick = { divider = "" }
        )
        CustomOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            text = gi,
            label = R.string.label_gi,
            placeholder = R.string.placeholder_gi,
            keyboardType = KeyboardType.Number,
            onTextChanged = {
                gi = it
                state.gi.value = gi
            },
            onClearClick = { gi = "" }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Green, shape = RoundedCornerShape(MaterialTheme.spacing.small))
                .clip(RoundedCornerShape(MaterialTheme.spacing.small))
                .clickable { onResultClicked() },
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = MaterialTheme.spacing.small),
                text = stringResource(id = R.string.button_result),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign =  TextAlign.Center
            )
        }
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
package com.seijind.insulincalculator.ui.home.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.seijind.insulincalculator.R
import com.seijind.insulincalculator.ui.composables.AutoComplete
import com.seijind.insulincalculator.ui.composables.CustomOutlinedTextField
import com.seijind.insulincalculator.ui.home.model.Food
import com.seijind.insulincalculator.ui.home.model.HomeUiState
import com.seijind.insulincalculator.ui.theme.InsulinCalculatorTheme
import com.seijind.insulincalculator.ui.theme.spacing
import kotlinx.coroutines.delay

private typealias OnResultClicked = () -> Unit
private typealias RemoveFood = (Food) -> Unit

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
        var food by remember { mutableStateOf(Food()) }
        var divider by remember { mutableStateOf(state.divider.value) }
        var gi by remember { mutableStateOf(state.gi.value) }

        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            maxLines = 1
        )
        AutoComplete(
            food = food,
            foods = state.foods,
            onTextChanged = { food.name.value = it },
            onItemClick = {
                food.name.value = it.name.value
                food.carbs.doubleValue = it.carbs.doubleValue
            }
        )
        CustomOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            text = food.grams.value,
            label = R.string.label_food_grams,
            placeholder = R.string.placeholder_food_grams,
            keyboardType = KeyboardType.Number,
            onTextChanged = {
                if (it.isEmpty()){
                    food.grams.value = it
                } else {
                    food.grams.value = when (it.toDoubleOrNull()) {
                        null -> food.grams.value
                        else -> it
                    }
                }
            },
            onClearClick = { food.grams.value = "" }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.Green,
                    shape = RoundedCornerShape(MaterialTheme.spacing.small)
                )
                .clip(RoundedCornerShape(MaterialTheme.spacing.small))
                .clickable {
                    if (food.name.value.isNotEmpty() && food.grams.value.isNotEmpty()) {
                        state.selectedFoods.add(food)
                        food = Food(
                            name = mutableStateOf(""),
                            carbs = mutableDoubleStateOf(0.0),
                            grams = mutableStateOf("")
                        )
                    }
                },
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
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            state.selectedFoods.forEach { food ->
                RemovableRow(
                    food = food,
                    removeFood = { state.selectedFoods.remove(it) }
                )
            }
        }
        CustomOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            text = divider,
            label = R.string.label_divider,
            placeholder = R.string.placeholder_divider,
            keyboardType = KeyboardType.Number,
            onTextChanged = {
                if (it.isEmpty()){
                    divider = it
                    state.divider.value = divider
                } else {
                    divider = when (it.toIntOrNull()) {
                        null -> divider
                        else -> it
                    }
                    state.divider.value = when (it.toIntOrNull()) {
                        null -> state.divider.value
                        else -> it
                    }
                }
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
                if (it.isEmpty()){
                    gi = it
                    state.gi.value = gi
                } else {
                    gi = when (it.toIntOrNull()) {
                        null -> gi
                        else -> it
                    }
                    state.gi.value = when (it.toIntOrNull()) {
                        null -> state.gi.value
                        else -> it
                    }
                }
            },
            onClearClick = { gi = "" }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.Green,
                    shape = RoundedCornerShape(MaterialTheme.spacing.small)
                )
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
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium),
            text = stringResource(id = R.string.result_text, state.result.value),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun RemovableRow(
    food: Food,
    removeFood: RemoveFood
) {
    var clickCount by remember { mutableIntStateOf(0) }
    var lastClickTime by remember { mutableLongStateOf(0L) }

    LaunchedEffect(clickCount) {
        if (clickCount > 0) {
            delay(1000)
            clickCount = 0
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.small)
            .clickable {
                val currentTime = System.currentTimeMillis()
                if (currentTime - lastClickTime < 500) {
                    clickCount++
                    if (clickCount == 2) {
                        removeFood(food)
                        clickCount = 0
                    }
                } else {
                    clickCount = 1
                }
                lastClickTime = currentTime
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = food.name.value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = stringResource(id = R.string.grams, food.grams.value),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
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
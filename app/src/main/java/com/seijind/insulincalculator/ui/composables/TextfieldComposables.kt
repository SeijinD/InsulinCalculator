package com.seijind.insulincalculator.ui.composables


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.seijind.insulincalculator.R
import com.seijind.insulincalculator.ui.home.model.Food
import com.seijind.insulincalculator.ui.theme.spacing

internal typealias OnTextChanged = (String) -> Unit
internal typealias OnClearClicked = () -> Unit

@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    text: String,
    label: Int,
    placeholder: Int,
    keyboardType: KeyboardType = KeyboardType.Text,
    onClearClick: OnClearClicked,
    onTextChanged: OnTextChanged
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    var showClearButton by remember { mutableStateOf(false) }
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                showClearButton = (focusState.isFocused)
            },
        value = text,
        onValueChange = { onTextChanged(it) },
        label = {
            Text(
                text = stringResource(id = label),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        },
        placeholder = {
            Text(
                text = stringResource(id = placeholder),
                fontSize = 14.sp,
                color = Color.Black.copy(alpha = 0.6f)
            )
        },
        textStyle = TextStyle(
            fontSize = 14.sp,
            color = Color.Black
        ),
        singleLine = true,
        trailingIcon = {
            if (showClearButton) {
                IconButton(onClick = { onClearClick() }) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "Clear")
                }
            }
        },
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
        }),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = keyboardType
        )
    )
}

@Composable
fun AutoComplete(
    food: Food,
    foods: List<Food>,
    onTextChanged: OnTextChanged,
    onItemClick: (Food) -> Unit = {},
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    expanded = false
                }
            )
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        textFieldSize = coordinates.size.toSize()
                    },
                value = food.name.value,
                onValueChange = { onTextChanged(it) },
                label = {
                    Text(
                        text = stringResource(id = R.string.label_foods),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.placeholder_food),
                        fontSize = 14.sp,
                        color = Color.Black.copy(alpha = 0.6f)
                    )
                },
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Black
                ),
                singleLine = true,
                trailingIcon = {
                    val icon = if (expanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = icon,
                            contentDescription = "arrow",
                            tint = Color.Black
                        )
                    }
                },
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                }),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                )
            )
        }
        AnimatedVisibility(visible = expanded) {
            Card(
                modifier = Modifier
                    .padding(top = MaterialTheme.spacing.extraSmall)
                    .width(textFieldSize.width.dp),
                shape = RoundedCornerShape(MaterialTheme.spacing.small)
            ) {
                LazyColumn(
                    modifier = Modifier.heightIn(max = 150.dp),
                ) {
                    if (food.name.value.isNotEmpty()) {
                        items(
                            foods.filter {
                                it.name.value.lowercase()
                                    .contains(food.name.value.lowercase()) || it.name.value.lowercase()
                                    .contains("others")
                            }
                                .sortedBy { it.name.value }
                        ) { newFood ->
                            FoodItem(
                                title = newFood.name.value,
                                onSelect = {
                                    food.name.value = newFood.name.value
                                    food.carbs.doubleValue = newFood.carbs.doubleValue
                                    onItemClick(food)
                                    expanded = false
                                }
                            )
                        }
                    } else {
                        items(
                            foods.sortedBy { it.name.value }
                        ) { newFood ->
                            FoodItem(
                                title = newFood.name.value,
                                onSelect = {
                                    food.name.value = newFood.name.value
                                    food.carbs.doubleValue = newFood.carbs.doubleValue
                                    onItemClick(food)
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FoodItem(
    title: String,
    onSelect: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect(title) }
            .padding(MaterialTheme.spacing.small)
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
    }
}

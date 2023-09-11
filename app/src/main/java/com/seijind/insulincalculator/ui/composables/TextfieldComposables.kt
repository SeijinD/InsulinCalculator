package com.seijind.insulincalculator.ui.composables


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
fun <T> AutoCompleteTextView(
    modifier: Modifier,
    query: String,
    placeholder: Int,
    queryLabel: Int,
    onTextChanged: OnTextChanged,
    predictions: List<T>,
    onClearClick: OnClearClicked,
    onItemClick: (T) -> Unit = {},
    itemContent: @Composable (T) -> Unit = {}
) {

    val view = LocalView.current
    val lazyListState = rememberLazyListState()
    LazyColumn(
        state = lazyListState,
        modifier = modifier.heightIn(max = TextFieldDefaults.MinHeight * 6)
    ) {
        item {
            CustomOutlinedTextField(
                text = query,
                label = queryLabel,
                placeholder = placeholder,
                onTextChanged = { onTextChanged(it) },
                onClearClick = {
                    onClearClick()
                }
            )
        }
        if (predictions.isNotEmpty()) {
            items(predictions) { prediction ->
                Row(
                    Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable {
                            view.clearFocus()
                            onItemClick(prediction)
                        }
                ) {
                    itemContent(prediction)
                }
            }
        }
    }
}

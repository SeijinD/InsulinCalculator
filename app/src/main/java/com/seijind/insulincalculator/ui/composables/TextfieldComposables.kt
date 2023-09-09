package com.seijind.insulincalculator.ui.composables


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

internal typealias OnNextClicked = (String) -> Unit

@Composable
internal fun CustomTextfield(
    onNextClicked: OnNextClicked,
    placeHolder: Int,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by remember { mutableStateOf("") }
    val trailingIconView = @Composable {
        IconButton(
            onClick = { text = "" },
        ) {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = "",
                tint = Color.Black
            )
        }
    }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        value = text,
        onValueChange = {
            text = it
            onNextClicked(text)
        },
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
        trailingIcon = trailingIconView,
        placeholder = {
            Text(
                text = stringResource(id = placeHolder),
                fontSize = 14.sp,
                color = Color.Black.copy(alpha = 0.6f)
            )
        },
        textStyle = TextStyle(fontSize = 14.sp),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(onSearch = {
            onNextClicked(text)
            keyboardController?.hide()
        })
    )
}
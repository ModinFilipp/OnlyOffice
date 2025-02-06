package com.example.onlyofficetest.presentation.composeComponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.onlyofficetest.ui.theme.OnlyOfficeTestTheme

@Composable
fun LoginInputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeHolder: String,
    icon: ImageVector
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeHolder) },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        shape = RectangleShape,
        modifier = modifier.fillMaxWidth()
    )
}


@Preview
@Composable
fun LoginInputFieldPreview(modifier: Modifier = Modifier) {
    OnlyOfficeTestTheme {
        var text by remember { mutableStateOf("") }
        LoginInputField(
            value = text,
            onValueChange = { text = it },
            placeHolder = "Введите текст",
            icon = Icons.Default.AccountCircle
        )
    }
}
package com.example.onlyofficetest.presentation.composeComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.onlyofficetest.ui.theme.OnlyOfficeTestTheme

@Composable
fun DocumentItem(
    modifier: Modifier = Modifier,
    title: String,
    imageVector: ImageVector,
    onItemClick: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .clickable { onItemClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = title
            )
        }
        HorizontalDivider()
    }
}

@Preview
@Composable
private fun DocumentItemPreview(modifier: Modifier = Modifier) {
    OnlyOfficeTestTheme {
        DocumentItem(
            title = "dsgjg kkjgsk",
            imageVector = Icons.Default.AddCircle,
            onItemClick = {}
        )
    }
}
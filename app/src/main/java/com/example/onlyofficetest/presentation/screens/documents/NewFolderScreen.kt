package com.example.onlyofficetest.presentation.screens.documents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.onlyofficetest.presentation.composeComponents.DocumentItem
import com.example.onlyofficetest.ui.theme.OnlyOfficeTestTheme

@Composable
fun NewFolderScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    NewFolderContent(
        modifier = modifier,
        onBackClick = { navController.popBackStack() }
    )
}

@Composable
private fun NewFolderContent(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        IconButton(
            onClick = onBackClick
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            Text(
                modifier = Modifier.padding(top = 70.dp, bottom = 10.dp),
                text = "New Folder",
                style = MaterialTheme.typography.headlineMedium
            )
            DocumentItem(
                modifier = Modifier.padding(horizontal = 5.dp),
                title = "New Document.docx",
                imageVector = Icons.Default.Menu,
                onItemClick = { }
            )
        }
    }
}


@Preview
@Composable
fun NewFolderContentPreview(modifier: Modifier = Modifier) {
    OnlyOfficeTestTheme {
        NewFolderContent(
            onBackClick = {}
        )
    }
}
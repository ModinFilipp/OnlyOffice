package com.example.onlyofficetest.presentation.screens.documents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.onlyofficetest.R
import com.example.onlyofficetest.presentation.composeComponents.DocumentItem
import com.example.onlyofficetest.ui.theme.OnlyOfficeTestTheme

@Composable
fun DocumentsScreen(
    modifier: Modifier = Modifier,
) {
    val localNavController = rememberNavController()

    NavHost(
        navController = localNavController,
        startDestination = DocumentsRoutes.Documents.route,
        modifier = modifier
    ) {
        composable(DocumentsRoutes.Documents.route) {
            DocumentsContent(
                modifier = modifier,
                newFolderClick = { localNavController.navigate(DocumentsRoutes.NewFolder.route) }
            )
        }
        composable(DocumentsRoutes.NewFolder.route) {
            NewFolderScreen(navController = localNavController)
        }
    }
}

@Composable
private fun DocumentsContent(
    modifier: Modifier = Modifier,
    newFolderClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 10.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 70.dp, bottom = 10.dp),
            text = "Documents",
            style = MaterialTheme.typography.headlineMedium
        )
        DocumentItem(
            modifier = Modifier.padding(horizontal = 5.dp),
            title = "New Folder",
            imageVector = ImageVector.vectorResource(R.drawable.outline_folder_24),
            onItemClick = { newFolderClick() }
        )
        DocumentItem(
            modifier = Modifier.padding(horizontal = 5.dp),
            title = "Sample Document.docx",
            imageVector = Icons.Default.Menu,
            onItemClick = {}
        )
    }
}

@Preview
@Composable
private fun DocumentsContentPreview(modifier: Modifier = Modifier) {
    OnlyOfficeTestTheme {
        DocumentsContent(
            newFolderClick = {}
        )
    }
}
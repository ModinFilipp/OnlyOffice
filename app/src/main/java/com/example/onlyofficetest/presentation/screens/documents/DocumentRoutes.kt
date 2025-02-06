package com.example.onlyofficetest.presentation.screens.documents

sealed class DocumentsRoutes(val route: String) {
    data object Documents : DocumentsRoutes("documents")
    data object NewFolder : DocumentsRoutes("documents/new_folder")
}
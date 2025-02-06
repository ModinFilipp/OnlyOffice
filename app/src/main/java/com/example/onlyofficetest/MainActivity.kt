package com.example.onlyofficetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.example.onlyofficetest.navigation.AppNavigation
import com.example.onlyofficetest.ui.theme.OnlyOfficeTestTheme
import org.koin.android.ext.android.getKoin

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            OnlyOfficeTestTheme {
                AppNavigation(dataStoreManager = getKoin().get())
            }
        }
    }
}


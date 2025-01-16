package com.example.myapplication

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.myapplication.restcountries.ui.CountryList
import com.example.myapplication.restcountries.ui.countryNavGraph
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun MainAppNavHost(
    appState: AppState = rememberAppState(),
    modifier: Modifier = Modifier
) {
    MyApplicationTheme {
        Scaffold(modifier = modifier.fillMaxSize().safeDrawingPadding()) { innerPadding ->
            NavHost(
                navController = appState.navController,
                startDestination = CountryList,
                modifier = Modifier.padding(innerPadding)
            ) {
                countryNavGraph(appState.navController)
            }
        }
    }
}


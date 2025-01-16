package com.example.myapplication.restcountries.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.example.myapplication.restcountries.ui.countryDetails.CountryDetailsRoute
import com.example.myapplication.restcountries.ui.countryList.CountryListRoute
import kotlinx.serialization.Serializable

@Serializable
data object CountryList

@Serializable
data class CountryDetails(val name: String)

fun NavController.navigateToDetails(
    name: String, navOptions: NavOptionsBuilder.() -> Unit = {}
) {
    navigate(route = CountryDetails(name)) {
        navOptions()
    }
}


fun NavGraphBuilder.countryNavGraph(navController: NavHostController) {
    composable<CountryList> {
        CountryListRoute(onClick = {
            navController.navigateToDetails(it)
        })
    }
    composable<CountryDetails> {
        CountryDetailsRoute(navController::navigateUp)
    }
}
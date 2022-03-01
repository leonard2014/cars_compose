package com.leonard.cars_compose.ui

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.leonard.cars_compose.R
import com.leonard.cars_compose.ui.theme.Cars_Theme

@Composable
fun CarsApp(mainViewModel: MainViewModel) {
    Cars_Theme {
        Scaffold(topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.app_name))
                }
            )
        }) {
            val navController = rememberNavController()
            NavHost(navController, startDestination = NavRoute.CarsList.name) {
                composable(route = NavRoute.CarsList.name) {
                    CarsListScreen(mainViewModel = mainViewModel)
                }
            }

            CarsListScreen(mainViewModel)
        }
    }
}

private enum class NavRoute {
    CarsList, CarDetails
}
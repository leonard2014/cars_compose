package com.leonard.cars_compose.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.leonard.cars_compose.R
import com.leonard.cars_compose.ui.model.UIState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController

@Composable
internal fun CarsListScreen(navController: NavController, mainViewModel: MainViewModel) {
    val uiState: UIState by mainViewModel.carsList
    when (uiState) {
        is UIState.Loading -> {
            ProgressBar()
        }
        is UIState.Content -> {
            CarsList((uiState as UIState.Content).list) {
                navController.navigate(NavRoute.CarDetails.name)
            }
        }
        is UIState.Empty -> {
            EmptyState()
        }
        else -> {
            ErrorState()
        }
    }
}

@Composable
private fun EmptyState() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.empty_message)
        )
    }
}

@Composable
private fun ErrorState() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.error_message)
        )
    }
}
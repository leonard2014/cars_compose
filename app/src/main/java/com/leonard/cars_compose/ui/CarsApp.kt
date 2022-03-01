package com.leonard.cars_compose.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.leonard.cars_compose.R
import com.leonard.cars_compose.ui.model.UIState
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
            val uiState: UIState by mainViewModel.carsList
            when (uiState) {
                is UIState.Loading -> {
                    ProgressBar()
                }
                is UIState.Content -> {
                    CarsList((uiState as UIState.Content).list)
                }
                is UIState.Empty -> {
                    EmptyState()
                }
                else -> {
                    ErrorState()
                }
            }
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
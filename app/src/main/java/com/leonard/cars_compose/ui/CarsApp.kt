package com.leonard.cars_compose.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leonard.cars_compose.R
import com.leonard.cars_compose.ui.model.CarsUIItem
import com.leonard.cars_compose.ui.model.CarsUIList
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
private fun CarsList(list: CarsUIList) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items = list) { car ->
            ListRow(car)
            Divider()
        }
    }
}

@Composable
@Preview
private fun PreviewCarsList() {
    CarsList(
        listOf(
            CarsUIItem(title = "2020 Mazda 3", price = "$20,000", image = null),
            CarsUIItem(
                title = "2019 very long long very long title very long",
                price = "$30,000",
                image = null
            )
        )
    )
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
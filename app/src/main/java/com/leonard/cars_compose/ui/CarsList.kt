package com.leonard.cars_compose.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leonard.cars_compose.ui.model.CarsUIItem
import com.leonard.cars_compose.ui.model.CarsUIList

@Composable
internal fun CarsList(list: CarsUIList, onItemClick: (car: CarsUIItem) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items = list) { car ->
            ListRow(car, onItemClick)
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
    ){}
}
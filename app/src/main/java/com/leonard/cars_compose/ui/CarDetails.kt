package com.leonard.cars_compose.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.leonard.cars_compose.ui.model.CarsUIItem

@Composable
internal fun CarDetails(car: CarsUIItem) {
    Text(text = "${car.title}")
}
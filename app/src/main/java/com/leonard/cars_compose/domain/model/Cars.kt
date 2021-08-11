package com.leonard.cars_compose.domain.model

import com.leonard.cars_compose.data.BASE_URL
import com.leonard.cars_compose.data.CarsResponse

typealias Cars = List<Car>

data class Car(
    val year: Int,
    val manufacturer: String,
    val make: String,
    val price: Int,
    val image: String?
)

fun CarsResponse.toDomainModel(): Cars {
    return map {
        Car(
            year = it.year,
            manufacturer = it.make.manufacturer,
            make = it.make.model,
            price = it.price,
            image = it.image?.let { url -> "$BASE_URL${url}" }
        )
    }
}
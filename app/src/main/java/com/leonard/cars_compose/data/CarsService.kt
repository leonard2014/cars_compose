package com.leonard.cars_compose.data

import retrofit2.http.GET

interface CarsService {

    @GET("cars.json")
    suspend fun getCars(): CarsResponse
}
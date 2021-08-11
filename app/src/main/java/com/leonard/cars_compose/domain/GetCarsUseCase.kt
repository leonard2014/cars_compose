package com.leonard.cars_compose.domain

import com.leonard.cars_compose.data.CarsService
import com.leonard.cars_compose.domain.model.Cars
import com.leonard.cars_compose.domain.model.toDomainModel
import java.lang.Exception

class GetCarsUseCase(private val carsService: CarsService) {

    suspend fun getCars() : Result<Cars> {
        return try {
            Result.success(carsService.getCars().toDomainModel())
        } catch (error: Exception) {
            Result.failure(error)
        }
    }
}
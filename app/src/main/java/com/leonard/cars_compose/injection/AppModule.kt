package com.leonard.cars_compose.injection

import com.leonard.cars_compose.ui.MainViewModel
import com.leonard.cars_compose.data.CarsService
import com.leonard.cars_compose.domain.GetCarsUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    single { provideCarsService(get()) }
    single { GetCarsUseCase(get()) }
    viewModel { MainViewModel(get()) }
}

private fun provideCarsService(retrofit: Retrofit): CarsService {
    return retrofit.create(CarsService::class.java)
}
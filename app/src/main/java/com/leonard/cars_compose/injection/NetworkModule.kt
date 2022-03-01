package com.leonard.cars_compose.injection

import com.leonard.cars_compose.data.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideRetrofit() }
}

private fun provideRetrofit(): Retrofit {
    val httpLogger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder().apply {
        addInterceptor(httpLogger)
    }.build()

    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(BASE_URL)
        .build()
}
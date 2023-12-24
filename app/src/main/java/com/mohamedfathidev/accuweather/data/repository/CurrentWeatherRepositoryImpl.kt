package com.mohamedfathidev.accuweather.data.repository

import com.mohamedfathidev.accuweather.data.remote.ApiService
import com.mohamedfathidev.accuweather.data.remote.model.CurrentWeather
import com.mohamedfathidev.accuweather.domain.repository.CurrentWeatherRepository

class CurrentWeatherRepositoryImpl(
    private val apiService: ApiService
) : CurrentWeatherRepository {
    override suspend fun getCurrentWeather(): CurrentWeather {
        return apiService.getCurrentWeather()
    }

    override suspend fun getForecasting(days: String): CurrentWeather {
        return apiService.getCurrentWeather(days = days)
    }
}
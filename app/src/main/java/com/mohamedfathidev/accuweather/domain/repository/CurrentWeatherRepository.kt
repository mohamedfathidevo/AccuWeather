package com.mohamedfathidev.accuweather.domain.repository

import com.mohamedfathidev.accuweather.data.remote.model.CurrentWeather

interface CurrentWeatherRepository {
    suspend fun getCurrentWeather(): CurrentWeather
    suspend fun getForecasting(days: String): CurrentWeather
}
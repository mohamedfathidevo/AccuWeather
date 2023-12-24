package com.mohamedfathidev.accuweather.data.remote

import com.mohamedfathidev.accuweather.data.remote.model.CurrentWeather
import com.mohamedfathidev.accuweather.util.Constant
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("forecast.json")
    suspend fun getCurrentWeather(
        @Query("key") key: String = Constant.apiKey,
        @Query("q") location: String = "London",
        @Query("days") days: String = "1",
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "no",
    ): CurrentWeather

}
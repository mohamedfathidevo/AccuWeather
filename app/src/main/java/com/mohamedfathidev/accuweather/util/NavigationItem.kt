package com.mohamedfathidev.accuweather.util

import com.mohamedfathidev.accuweather.R

sealed class NavigationItem(val route: String, val icon: Int, val title: String) {
    data object CurrentWeather :
        NavigationItem(Routes.weather, R.drawable.current_weather, "Current Weather")

    data object Forecasting :
        NavigationItem(Routes.forecasting, R.drawable.weather_forecasting, "Forecasting")

}
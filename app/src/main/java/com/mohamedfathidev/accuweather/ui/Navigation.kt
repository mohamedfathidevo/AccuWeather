package com.mohamedfathidev.accuweather.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mohamedfathidev.accuweather.ui.current_weather.CurrentWeatherScreen
import com.mohamedfathidev.accuweather.ui.forecasting.ForecastingScreen
import com.mohamedfathidev.accuweather.util.NavigationItem

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationItem.CurrentWeather.route) {
        composable(NavigationItem.CurrentWeather.route) {
            CurrentWeatherScreen(navController)
        }

        composable(NavigationItem.Forecasting.route) {
            ForecastingScreen(navController)
        }
    }
}
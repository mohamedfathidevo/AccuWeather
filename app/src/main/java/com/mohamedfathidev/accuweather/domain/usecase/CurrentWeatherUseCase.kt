package com.mohamedfathidev.accuweather.domain.usecase

import com.mohamedfathidev.accuweather.data.remote.model.CurrentWeather
import com.mohamedfathidev.accuweather.domain.repository.CurrentWeatherRepository
import com.mohamedfathidev.accuweather.util.ResponseState
import kotlinx.coroutines.flow.flow

class CurrentWeatherUseCase(
    private val currentWeatherRepository: CurrentWeatherRepository
) {
    suspend operator fun invoke() = flow<CurrentWeather> {
        emit(currentWeatherRepository.getCurrentWeather())
    }
}
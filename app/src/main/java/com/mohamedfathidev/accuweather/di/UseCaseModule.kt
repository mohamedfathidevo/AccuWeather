package com.mohamedfathidev.accuweather.di

import com.mohamedfathidev.accuweather.domain.repository.CurrentWeatherRepository
import com.mohamedfathidev.accuweather.domain.usecase.CurrentWeatherUseCase
import com.mohamedfathidev.accuweather.domain.usecase.ForecastingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideCurrentWeatherUseCase(currentWeatherRepository: CurrentWeatherRepository): CurrentWeatherUseCase {
        return CurrentWeatherUseCase(currentWeatherRepository)
    }

    @Singleton
    @Provides
    fun provideForecastingUseCase(currentWeatherRepository: CurrentWeatherRepository): ForecastingUseCase {
        return ForecastingUseCase(currentWeatherRepository)
    }
}
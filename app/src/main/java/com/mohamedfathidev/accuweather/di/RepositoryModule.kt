package com.mohamedfathidev.accuweather.di

import com.mohamedfathidev.accuweather.data.remote.ApiService
import com.mohamedfathidev.accuweather.data.repository.CurrentWeatherRepositoryImpl
import com.mohamedfathidev.accuweather.domain.repository.CurrentWeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideCurrentWeatherRepo(
        apiService: ApiService
    ): CurrentWeatherRepository {
        return CurrentWeatherRepositoryImpl(
            apiService
        )
    }
}
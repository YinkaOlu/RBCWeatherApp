package com.yinkaolu.rbcweatherapp.data

import com.yinkaolu.rbcweatherapp.data.api.OpenWeatherService
import com.yinkaolu.rbcweatherapp.data.api.RealOpenWeatherService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Hilt Module to provide the necessary Data components
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindOpenWeatherRepository(
        realOpenWeatherRepository: RealOpenWeatherRepository
    ): OpenWeatherRepository

    @Binds
    abstract fun bindOpenWeatherService(
        analyticsServiceImpl: RealOpenWeatherService
    ): OpenWeatherService
}
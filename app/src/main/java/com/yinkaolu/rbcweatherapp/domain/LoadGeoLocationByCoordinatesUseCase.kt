package com.yinkaolu.rbcweatherapp.domain

import com.yinkaolu.rbcweatherapp.data.OpenWeatherRepository
import com.yinkaolu.rbcweatherapp.data.api.model.geo.GeoLocation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Use case that represents the loading of Geolocation Information
 * for a give coordinates (latitude & longitude).
 * Returns full Geolocation data for the first (most likely) match
 */
class LoadGeoLocationByCoordinatesUseCase @Inject constructor(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
    val openWeatherRepository: OpenWeatherRepository
) {

    suspend fun invoke(
        lat: Double,
        lon: Double,
    ): GeoLocation = withContext(dispatcher) {
        val locations = openWeatherRepository.findLocationByCoordinates(
            longitude = "$lon",
            latitude = "$lat"
        )

        if (locations.isNotEmpty()) {
            locations.first()
        } else {
            throw Exception("No locations found.")
        }
    }
}
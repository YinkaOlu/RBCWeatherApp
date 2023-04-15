package com.yinkaolu.rbcweatherapp.domain

import com.yinkaolu.rbcweatherapp.data.OpenWeatherRepository
import com.yinkaolu.rbcweatherapp.data.api.model.geo.GeoLocation
import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.UserLocationSearchInput
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoadGeoLocationUseCase @Inject constructor(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
    val openWeatherRepository: OpenWeatherRepository
) {

    suspend fun invoke(
        lastGeoLocation: GeoLocation?,
        userLocationSearch: UserLocationSearchInput? = null,
    ): GeoLocation = withContext(dispatcher) {
        val locations = openWeatherRepository.findLocation(
            city = userLocationSearch?.cityName ?: lastGeoLocation?.name.orEmpty(),
            state = userLocationSearch?.state ?: lastGeoLocation?.state,
            country = userLocationSearch?.country ?: lastGeoLocation?.country
        )

        if (locations.isNotEmpty()) {
            locations.first()
        } else {
            throw Exception("No locations found.")
        }
    }
}
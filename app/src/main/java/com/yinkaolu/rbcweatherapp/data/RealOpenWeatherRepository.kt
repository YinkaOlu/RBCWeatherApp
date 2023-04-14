package com.yinkaolu.rbcweatherapp.data

import android.accounts.NetworkErrorException
import com.yinkaolu.rbcweatherapp.data.api.OpenWeatherService
import com.yinkaolu.rbcweatherapp.data.api.OpenWeatherResponse
import com.yinkaolu.rbcweatherapp.data.api.geo.GeoLocationList
import com.yinkaolu.rbcweatherapp.data.api.weather.WeatherReport
import javax.inject.Inject

class RealOpenWeatherRepository @Inject constructor(
    val openWeatherService: OpenWeatherService
) : OpenWeatherRepository {

    override suspend fun loadCurrentWeatherReport(
        latitude: String,
        longitude: String
    ): WeatherReport {
        val loadCurrentWeatherReportResponse = openWeatherService.retrieveCurrentWeather(
            latitude = latitude,
            longitude = longitude
        )

        return when(loadCurrentWeatherReportResponse) {
            is OpenWeatherResponse.Success<WeatherReport> -> {
                loadCurrentWeatherReportResponse.details
            }
            is OpenWeatherResponse.Error -> {
                // TODO: Handle network error
                throw java.lang.Exception("Network error")
            }
        }
    }

    override suspend fun findLocation(
        city: String,
        state: String?,
        country: String?
    ): GeoLocationList {

        val foundLocationResponse = openWeatherService.findGeoLocations(
            city = city,
            state = state,
            country = country
        )

        return when(foundLocationResponse) {
            is OpenWeatherResponse.Success<GeoLocationList> -> {
                foundLocationResponse.details
            }
            is OpenWeatherResponse.Error -> {
                when(foundLocationResponse.type) {
                    OpenWeatherResponse.Error.ErrorType.NETWORK_ERROR ->
                        throw NetworkErrorException("Network error")
                    OpenWeatherResponse.Error.ErrorType.EMPTY_RESPONSE ->
                        throw NetworkErrorException("Response body was empty")
                }
            }
        }
    }
}
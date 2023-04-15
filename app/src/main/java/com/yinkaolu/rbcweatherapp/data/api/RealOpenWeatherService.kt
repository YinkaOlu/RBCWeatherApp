package com.yinkaolu.rbcweatherapp.data.api

import com.yinkaolu.rbcweatherapp.data.api.model.geo.GeoLocationList
import com.yinkaolu.rbcweatherapp.data.api.model.weather.ForcastReport
import com.yinkaolu.rbcweatherapp.data.api.model.weather.WeatherReport
import retrofit2.GsonConverterFactory
import retrofit2.Retrofit
import javax.inject.Inject

class RealOpenWeatherService @Inject constructor() : OpenWeatherService {
    private var openWeatherAPIService: OpenWeatherAPI
    private val apiKey = "e1446afca0b722b83a6a5ffeaae20838"

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        openWeatherAPIService = retrofit.create(OpenWeatherAPI::class.java)
    }

    override suspend fun retrieveForcast(
        latitude: String,
        longitude: String
    ): OpenWeatherResponse<ForcastReport> {
        val response = openWeatherAPIService.getForcast(
            latitude = latitude,
            longitude = longitude,
            apiKey = apiKey
        ).execute()

        return response.body()?.let {
            if (response.isSuccessful) {
                OpenWeatherResponse.Success(it)
            } else {
                OpenWeatherResponse.Error(OpenWeatherResponse.Error.ErrorType.NETWORK_ERROR)
            }
        } ?: OpenWeatherResponse.Error(OpenWeatherResponse.Error.ErrorType.EMPTY_RESPONSE)

    }

    override suspend fun retrieveCurrentWeather(
        latitude: String,
        longitude: String
    ): OpenWeatherResponse<WeatherReport> {
        val response = openWeatherAPIService.getWeatherDetails(
            latitude = latitude,
            longitude = longitude,
            apiKey = apiKey
        ).execute()

        return response.body()?.let {
            if (response.isSuccessful) {
                OpenWeatherResponse.Success(it)
            } else {
                OpenWeatherResponse.Error(OpenWeatherResponse.Error.ErrorType.NETWORK_ERROR)
            }
        } ?: OpenWeatherResponse.Error(OpenWeatherResponse.Error.ErrorType.EMPTY_RESPONSE)
    }

    override suspend fun findGeoLocations(
        city: String,
        state: String?,
        country: String?
    ): OpenWeatherResponse<GeoLocationList> {
        var locationQuery = city

        state?.let {
            locationQuery = "$locationQuery,$it"
        }

        country?.let {
            locationQuery = "$locationQuery,$it"
        }

        val geoLocationResponse = openWeatherAPIService.findGeoLocation(
            cityStateCountryQuery = locationQuery,
            apiKey = apiKey
        ).execute()

        return geoLocationResponse.body()?.let {
            if (geoLocationResponse.isSuccessful) {
                OpenWeatherResponse.Success(it)
            } else {
                OpenWeatherResponse.Error(OpenWeatherResponse.Error.ErrorType.NETWORK_ERROR)
            }
        } ?: OpenWeatherResponse.Error(OpenWeatherResponse.Error.ErrorType.EMPTY_RESPONSE)
    }

    override suspend fun findGeoLocationsByCoordinate(latitude: String, longitude: String): OpenWeatherResponse<GeoLocationList> {
        val geoLocationResponse = openWeatherAPIService.findLocationCoordinates(
            lat = latitude,
            lon = longitude,
            apiKey = apiKey
        ).execute()

        return geoLocationResponse.body()?.let {
            if (geoLocationResponse.isSuccessful) {
                OpenWeatherResponse.Success(it)
            } else {
                OpenWeatherResponse.Error(OpenWeatherResponse.Error.ErrorType.NETWORK_ERROR)
            }
        } ?: OpenWeatherResponse.Error(OpenWeatherResponse.Error.ErrorType.EMPTY_RESPONSE)
    }
}

sealed interface OpenWeatherResponse<S> {
    data class Success<S>(
        val details: S
    ) : OpenWeatherResponse<S>

    class Error<S>(
        val type: ErrorType
    ): OpenWeatherResponse<S> {
        enum class ErrorType {
            NETWORK_ERROR,
            EMPTY_RESPONSE
        }
    }
}
package com.yinkaolu.rbcweatherapp.data

import com.yinkaolu.rbcweatherapp.data.api.OpenWeatherService
import com.yinkaolu.rbcweatherapp.data.api.OpenWeatherResponse
import com.yinkaolu.rbcweatherapp.data.api.geo.GeoLocation
import com.yinkaolu.rbcweatherapp.data.api.weather.WeatherReport
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class RealOpenWeatherRepositoryTest {
    val openWeatherService = mock<OpenWeatherService>()
    lateinit var openWeatherRepository: OpenWeatherRepository

    @Before
    fun setup() {
        openWeatherRepository = RealOpenWeatherRepository(
            openWeatherService = openWeatherService
        )
    }

    @Test
    fun findGeoLocations_successResponse() = runTest {
        `when`(openWeatherService
            .findGeoLocations(anyString(), eq(null), eq(null))
        ).thenReturn(
            OpenWeatherResponse.Success(
                details = listOf(
                    GeoLocation(
                        name = "Toronto"
                    )
                )
            )
        )

        val locationList =
            openWeatherRepository.findLocation("Toronto")

        assert(locationList.size == 1)
        assert(locationList[0].name == "Toronto")
    }

    @Test(expected = java.lang.Exception::class)
    fun findGeoLocations_errorResponse() = runTest {
        `when`(openWeatherService
            .findGeoLocations(anyString(), anyString(), anyString())
        ).thenReturn(
            OpenWeatherResponse.Error("Network error")
        )

        val locationList =
            openWeatherRepository.findLocation("Toronto")
    }

    @Test
    fun loadCurrentWeather_successResponse() = runTest {
        `when`(openWeatherService.retrieveCurrentWeather(anyString(), anyString())).thenReturn(
            OpenWeatherResponse.Success(
                details = WeatherReport(
                    cityID = "123"
                )
            )
        )
        val weatherReport =
            openWeatherRepository.loadCurrentWeatherReport("", "")

        assert(weatherReport.cityID == "123")
    }

    @Test(expected = java.lang.Exception::class)
    fun loadCurrentWeather_networkErrorResponse() = runTest {
        `when`(openWeatherService.retrieveCurrentWeather(anyString(), anyString())).thenReturn(
            OpenWeatherResponse.Error("Network error")
        )
        openWeatherRepository.loadCurrentWeatherReport("", "")
    }
}
package com.yinkaolu.rbcweatherapp.data

import com.yinkaolu.rbcweatherapp.data.api.RemoteWeatherService
import com.yinkaolu.rbcweatherapp.data.api.WeatherServiceResponse
import com.yinkaolu.rbcweatherapp.data.api.model.WeatherReport
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class RealWeatherReportRepositoryTest {
    val remoteWeatherService = mock<RemoteWeatherService>()
    lateinit var weatherReportRepository: WeatherReportRepository

    @Before
    fun setup() {
        weatherReportRepository = RealWeatherReportRepository(
            remoteWeatherService = remoteWeatherService
        )
    }

    @Test
    fun loadCurrentWeather_successResponse() = runTest {
        `when`(remoteWeatherService.retrieveCurrentWeather(anyString(), anyString())).thenReturn(
            WeatherServiceResponse.Success(
                details = WeatherReport(
                    cityID = "123"
                )
            )
        )
        val weatherReport =
            weatherReportRepository.loadCurrentWeatherReport("", "")

        assert(weatherReport.cityID == "123")
    }

    @Test(expected = java.lang.Exception::class)
    fun loadCurrentWeather_networkErrorResponse() = runTest {
        `when`(remoteWeatherService.retrieveCurrentWeather(anyString(), anyString())).thenReturn(
            WeatherServiceResponse.Error("Network error")
        )
        weatherReportRepository.loadCurrentWeatherReport("", "")
    }
}
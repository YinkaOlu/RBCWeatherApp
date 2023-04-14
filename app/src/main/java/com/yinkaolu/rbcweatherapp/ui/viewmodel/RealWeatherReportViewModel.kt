package com.yinkaolu.rbcweatherapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yinkaolu.rbcweatherapp.data.OpenWeatherRepository
import com.yinkaolu.rbcweatherapp.data.api.weather.WeatherReport
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

@HiltViewModel
class WeatherReportViewModel @Inject constructor(
    val openWeatherRepository: OpenWeatherRepository
): ViewModel() {
    private val _weatherReportUiState: MutableStateFlow<WeatherReportUiState> = MutableStateFlow(
        WeatherReportUiState.Loading
    )
    val weatherReportUiState: StateFlow<WeatherReportUiState> = _weatherReportUiState

    fun loadWeatherReport(info: LocationInfo? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val geoLocation = openWeatherRepository.findLocation(
                    city = info?.cityName ?: "Toronto",
                    state = info?.state,
                    country = info?.country
                ).firstOrNull()

                geoLocation?.let {
                    val weatherReport = openWeatherRepository.loadCurrentWeatherReport(
                        longitude = "${it.longitude}",
                        latitude = "${it.latitude}"
                    )
                    _weatherReportUiState.emit(
                        WeatherReportUiState.Ready(weatherReport.toWeatherSummary())
                    )
                }
            } catch (e: java.lang.Exception) {
                _weatherReportUiState.emit(WeatherReportUiState.Error(
                    message = e.message ?: "Unknown error occurred. Try again!"
                ))
            }
        }
    }

    private fun WeatherReport.toWeatherSummary() = WeatherSummary(
        icon = weather.firstOrNull()?.icon ?: "",
        cityName = cityName ?: "unknown",
        weatherDescription = weather.map { it.description }.joinToString ("\n" ),
        temperature = convertKelvinToCelsius(mainWeather?.temperature)?.toCelsiusString() ?: "unknown"
    )

    private fun convertKelvinToCelsius(kelvin: Double?): Double? =
        kelvin?.let {
            BigDecimal(it.minus(273.15)).setScale(2, RoundingMode.CEILING).toDouble()
        }

    private fun Double.toCelsiusString(): String = "$this°C"
}
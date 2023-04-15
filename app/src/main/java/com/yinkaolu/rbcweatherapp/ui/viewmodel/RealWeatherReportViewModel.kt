package com.yinkaolu.rbcweatherapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yinkaolu.rbcweatherapp.data.OpenWeatherRepository
import com.yinkaolu.rbcweatherapp.data.api.model.geo.GeoLocation
import com.yinkaolu.rbcweatherapp.data.api.model.weather.ForcastReport
import com.yinkaolu.rbcweatherapp.data.api.model.weather.WeatherReport
import com.yinkaolu.rbcweatherapp.ui.composable.screen.ErrorScreen
import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.*
import com.yinkaolu.rbcweatherapp.ui.viewmodel.uistate.WeatherReportUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class WeatherReportViewModel @Inject constructor(
    val openWeatherRepository: OpenWeatherRepository
): ViewModel() {
    private val mainDateFormater = SimpleDateFormat("EEEE (MMM dd)")
    private val forcastDateFormater = SimpleDateFormat("(HH:mm) MMM dd")
    private val _weatherReportUiState: MutableStateFlow<WeatherReportUiState> = MutableStateFlow(
        WeatherReportUiState.RequestLocationPermission
    )
    val weatherReportUiState: StateFlow<WeatherReportUiState> = _weatherReportUiState

    private var lastGeoLocation: GeoLocation? = null

    fun setLocationPermissionRejected() {
        viewModelScope.launch {
            _weatherReportUiState.emit(
                WeatherReportUiState.Error(
                    message = "Location permission denied. For current weather, please enable permission."
                )
            )
        }
    }
    fun setCurrentLocation(
        lat: Double,
        lon: Double,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val weatherReport = openWeatherRepository.loadCurrentWeatherReport(
                    longitude = "$lon",
                    latitude = "$lat"
                )

                val geoLocation = openWeatherRepository.findLocationByCoordinates(
                    latitude = lat.toString(),
                    longitude = lon.toString()
                ).firstOrNull()

                lastGeoLocation = geoLocation

                _weatherReportUiState.emit(
                    WeatherReportUiState.MainWeatherPage(
                        weatherSummary = weatherReport.toWeatherSummary(),
                        locationSummary = geoLocation.toLocationSummary()
                    )
                )
            }  catch (e: java.lang.Exception) {
                _weatherReportUiState.emit(
                    WeatherReportUiState.Error(
                        message = e.message ?: "Unknown error occurred. Try again!"
                    ))
            }
        }
    }

    fun setLocationPermissionState(
        isLocationPermissionGranted: Boolean
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!isLocationPermissionGranted) {
                _weatherReportUiState.emit(WeatherReportUiState.RequestLocationPermission)
            }
        }
    }

    fun loadWeatherReport(info: LocationInfo? = null) {
        if (_weatherReportUiState.value == WeatherReportUiState.RequestLocationPermission) {
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val geoLocation = openWeatherRepository.findLocation(
                    city = info?.cityName ?: lastGeoLocation?.name.orEmpty(),
                    state = info?.state ?: lastGeoLocation?.state,
                    country = info?.country ?: lastGeoLocation?.country
                ).firstOrNull()

                lastGeoLocation = geoLocation

                geoLocation?.let {
                    val weatherReport = openWeatherRepository.loadCurrentWeatherReport(
                        longitude = "${it.longitude}",
                        latitude = "${it.latitude}"
                    )
                    _weatherReportUiState.emit(
                        WeatherReportUiState.MainWeatherPage(
                            weatherSummary = weatherReport.toWeatherSummary(),
                            locationSummary = it.toLocationSummary()
                        )
                    )
                }
            } catch (e: java.lang.Exception) {
                _weatherReportUiState.emit(
                    WeatherReportUiState.Error(
                    message = e.message ?: "Unknown error occurred. Try again!"
                ))
            }
        }
    }

    fun loadForcastReport() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val geoLocation = openWeatherRepository.findLocation(
                    city = lastGeoLocation?.name.orEmpty(),
                    state = lastGeoLocation?.state,
                    country = lastGeoLocation?.country
                ).firstOrNull()

                geoLocation?.let {
                    val forcastReport = openWeatherRepository.loadForcastReport(
                        longitude = "${it.longitude}",
                        latitude = "${it.latitude}"
                    )
                    _weatherReportUiState.emit(
                        WeatherReportUiState.ForcastDetailPage(
                            forcastSummary = forcastReport.toForcastSummary(),
                            locationSummary = it.toLocationSummary()
                        )
                    )
                }
            } catch (e: java.lang.Exception) {
                _weatherReportUiState.emit(
                    WeatherReportUiState.Error(
                    message = e.message ?: "Unknown error occurred. Try again!"
                ))
            }
        }
    }

    private fun GeoLocation?.toLocationSummary() = this?.let {
        LocationSummary(
            cityName = it.name ?: "unknown",
            stateName = it.state,
            countryName = it.country
        )
    }

    private fun WeatherReport.toWeatherSummary() = WeatherSummary(
        icon = weather.firstOrNull()?.icon ?: "",
        weatherDescription = weather.map { it.description }.joinToString ("\n" ),
        temperature = convertKelvinToCelsius(mainWeather?.temperature)?.toCelsiusString() ?: "unknown",
        dateString = this.atTime?.let {
            val date = Date(it.toLong() * 1000)
            mainDateFormater.format(date)
        }.orEmpty()
    )

    private fun ForcastReport.toForcastSummary() = ForcastSummary(
        weatherSummaries = list.map {forcast ->
            WeatherSummary(
                icon = forcast.weather.firstOrNull()?.icon.orEmpty(),
                weatherDescription = forcast.weather.map { it.description }.joinToString ("\n" ),
                temperature = convertKelvinToCelsius(forcast.mainWeather?.temperature)?.toCelsiusString() ?: "unknown",
                dateString = forcast.time?.let {
                    val date = Date(it.toLong() * 1000)
                    forcastDateFormater.format(date)
                }.orEmpty()
            )
        }
    )

    private fun convertKelvinToCelsius(kelvin: Double?): Double? =
        kelvin?.let {
            BigDecimal(it.minus(273.15)).setScale(2, RoundingMode.CEILING).toDouble()
        }

    private fun Double.toCelsiusString(): String = "$this°C"
}
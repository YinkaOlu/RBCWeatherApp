package com.yinkaolu.rbcweatherapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yinkaolu.rbcweatherapp.data.api.model.geo.GeoLocation
import com.yinkaolu.rbcweatherapp.domain.*
import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.*
import com.yinkaolu.rbcweatherapp.ui.viewmodel.uistate.WeatherReportUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Main ViewModel that controls the state and display of the Weather report screens
 */
@HiltViewModel
class WeatherReportViewModel @Inject constructor(
    val loadGeoLocationUseCase: LoadGeoLocationUseCase,
    val loadForecastReportUseCase: LoadForecastReportUseCase,
    val loadWeatherReportUseCase: LoadWeatherReportUseCase,
    val loadGeoLocationByCoordinatesUseCase: LoadGeoLocationByCoordinatesUseCase,
    val loadWeatherReportByCoordinatesUseCase: LoadWeatherReportByCoordinatesUseCase
): ViewModel() {
    private val _weatherReportUiState: MutableStateFlow<WeatherReportUiState> = MutableStateFlow(
        WeatherReportUiState.RequestCurrentLocation
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
        viewModelScope.launch {
            try {

                val weatherReport = loadWeatherReportByCoordinatesUseCase.invoke(
                    latitude = lat,
                    longitude = lon
                )

                val geoLocation = loadGeoLocationByCoordinatesUseCase.invoke(
                    lat = lat,
                    lon = lon
                )

                lastGeoLocation = geoLocation

                _weatherReportUiState.emit(
                    WeatherReportUiState.MainWeatherPage(
                        summarizedWeatherReport = SummarizedWeatherReport(
                            weatherSummary = weatherReport.toWeatherSummary(),
                            locationSummary = lastGeoLocation.toLocationSummary()
                        )
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
        viewModelScope.launch {
            if (!isLocationPermissionGranted) {
                _weatherReportUiState.emit(WeatherReportUiState.RequestCurrentLocation)
            }
        }
    }

    fun loadWeatherReport(searchInput: UserLocationSearchInput? = null) {
        if (_weatherReportUiState.value == WeatherReportUiState.RequestCurrentLocation) {
            return
        }
        viewModelScope.launch {
            try {
                val geoLocation = loadGeoLocationUseCase.invoke(
                    lastGeoLocation = lastGeoLocation,
                    userLocationSearch = searchInput
                )

                lastGeoLocation = geoLocation

                val summarizedWeatherReport = loadWeatherReportUseCase.invoke(
                    lastGeoLocation = geoLocation
                )

                _weatherReportUiState.emit(
                    WeatherReportUiState.MainWeatherPage(
                        summarizedWeatherReport = summarizedWeatherReport
                    )
                )
            } catch (e: java.lang.Exception) {
                _weatherReportUiState.emit(
                    WeatherReportUiState.Error(
                    message = e.message ?: "Unknown error occurred. Try again!"
                ))
            }
        }
    }

    fun loadForcastReport() {
        viewModelScope.launch {
            try {
                val updatedLocation = loadGeoLocationUseCase.invoke(lastGeoLocation)
                lastGeoLocation = updatedLocation
                val fullForecastSummary = loadForecastReportUseCase.invoke(updatedLocation)

                _weatherReportUiState.emit(
                    WeatherReportUiState.ForecastDetailPage(
                        summarizedForecast = fullForecastSummary
                    )
                )
            } catch (e: java.lang.Exception) {
                _weatherReportUiState.emit(
                    WeatherReportUiState.Error(
                    message = e.message ?: "Unknown error occurred. Try again!"
                ))
            }
        }
    }
}
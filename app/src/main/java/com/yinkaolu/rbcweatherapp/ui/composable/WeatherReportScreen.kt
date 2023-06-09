package com.yinkaolu.rbcweatherapp.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.google.accompanist.permissions.*
import com.yinkaolu.rbcweatherapp.R
import com.yinkaolu.rbcweatherapp.ui.composable.screen.ErrorScreen
import com.yinkaolu.rbcweatherapp.ui.composable.screen.ForecastDetailScreen
import com.yinkaolu.rbcweatherapp.ui.composable.screen.LoadingScreen
import com.yinkaolu.rbcweatherapp.ui.composable.screen.MainWeatherReportScreen
import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.UserLocationSearchInput
import com.yinkaolu.rbcweatherapp.ui.viewmodel.uistate.WeatherReportUiState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WeatherReportScreen(
    state: WeatherReportUiState,
    onSearchLocation: (userLocationSearchInput: UserLocationSearchInput) -> Unit,
    onPresentForecastDetails: () -> Unit,
    onReturnToMain: () -> Unit,
    onLocationPermissionGranted: (Boolean) -> Unit
) {
    when (state) {
        is WeatherReportUiState.Error -> ErrorScreen(
            onSearchLocation = onSearchLocation,
            errorMessage = state.message
        )
        WeatherReportUiState.Loading -> LoadingScreen()
        is WeatherReportUiState.MainWeatherPage -> MainWeatherReportScreen(
            weatherSummary = state.summarizedWeatherReport.weatherSummary,
            locationSummary = state.summarizedWeatherReport.locationSummary,
            onSearchLocation = onSearchLocation,
            onViewMore = onPresentForecastDetails
        )
        is WeatherReportUiState.ForecastDetailPage -> ForecastDetailScreen(
            forecastSummary = state.summarizedForecast.forecastSummary,
            locationSummary = state.summarizedForecast.locationSummary,
            onBack = onReturnToMain
        )
        WeatherReportUiState.RequestCurrentLocation -> {
            val locationPermissionState = rememberMultiplePermissionsState(
                listOf(
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            )


            if (locationPermissionState.allPermissionsGranted) {
                onLocationPermissionGranted(true)
            } else {
                Column {
                    Text(stringResource(id = R.string.location_permission_rationale))
                    Button(onClick = { locationPermissionState.launchMultiplePermissionRequest() }) {
                        Text(stringResource(id = R.string.request_location_permission))
                    }
                }
            }
        }
    }
}
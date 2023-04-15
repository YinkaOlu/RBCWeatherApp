package com.yinkaolu.rbcweatherapp

import com.google.android.gms.location.LocationCallback
import android.os.Bundle
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.gms.location.*
import com.yinkaolu.rbcweatherapp.ui.theme.RBCWeatherAppTheme
import com.yinkaolu.rbcweatherapp.ui.viewmodel.WeatherReportViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.yinkaolu.rbcweatherapp.ui.WeatherReportScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val weatherReportViewModel by viewModels<WeatherReportViewModel>()

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var retrieveLocationCallback: LocationCallback
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpLocationListener()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                weatherReportViewModel.weatherReportUiState.collect { state ->
                    setContent {
                        RBCWeatherAppTheme {
                            Surface(
                                modifier = Modifier.fillMaxSize(),
                                color = MaterialTheme.colors.background
                            ) {
                                WeatherReportScreen(
                                    state = state,
                                    onSearchLocation = weatherReportViewModel::loadWeatherReport,
                                    onPresentForecastDetails = weatherReportViewModel::loadForcastReport,
                                    onReturnToMain = weatherReportViewModel::loadWeatherReport,
                                    onLocationPermissionGranted = { permissionGranted ->
                                        if (permissionGranted) {
                                            // Get Location and pass to view model
                                            retrieveLastKnownLocation()
                                        } else {
                                            // View permissions rejected, display error
                                            weatherReportViewModel.setLocationPermissionRejected()
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }

        weatherReportViewModel.setLocationPermissionState(
            checkCallingOrSelfPermission(ACCESS_COARSE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED
        )
        weatherReportViewModel.loadWeatherReport()
    }

    private fun setUpLocationListener() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        retrieveLocationCallback = object : LocationCallback() {
            override fun onLocationResult(location: LocationResult) {
                super.onLocationResult(location)
                location.lastLocation?.let {
                    weatherReportViewModel.setCurrentLocation(
                        lat = it.latitude,
                        lon = it.longitude
                    )
                    fusedLocationClient.removeLocationUpdates(retrieveLocationCallback)
                }
            }
        }
    }

    private fun retrieveLastKnownLocation() {
        val hasLocationPermission = checkCallingOrSelfPermission(ACCESS_COARSE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED && checkCallingOrSelfPermission(
            ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED

        if (hasLocationPermission) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    weatherReportViewModel.setCurrentLocation(
                        lat = location.latitude,
                        lon = location.longitude
                    )
                } else {
                    fusedLocationClient.requestLocationUpdates(
                        LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000).build(),
                        retrieveLocationCallback,
                        null
                    )
                }
            }
        }

    }
}
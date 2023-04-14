package com.yinkaolu.rbcweatherapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.yinkaolu.rbcweatherapp.ui.composable.screen.ErrorScreen
import com.yinkaolu.rbcweatherapp.ui.composable.screen.LoadingScreen
import com.yinkaolu.rbcweatherapp.ui.composable.screen.MainWeatherReportScreen
import com.yinkaolu.rbcweatherapp.ui.theme.RBCWeatherAppTheme
import com.yinkaolu.rbcweatherapp.ui.viewmodel.WeatherReportUiState
import com.yinkaolu.rbcweatherapp.ui.viewmodel.WeatherReportViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val weatherReportViewModel by viewModels<WeatherReportViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                weatherReportViewModel.weatherReportUiState.collect { state ->
                    setContent {
                        RBCWeatherAppTheme {
                            // A surface container using the 'background' color from the theme
                            Surface(
                                modifier = Modifier.fillMaxSize(),
                                color = MaterialTheme.colors.background
                            ) {
                                when(state) {
                                    is WeatherReportUiState.Error -> {
                                        Toast.makeText(
                                            this@MainActivity,
                                            state.message,
                                            Toast.LENGTH_LONG)
                                            .show()
                                        ErrorScreen(
                                            onSearchLocation = {
                                                weatherReportViewModel.loadWeatherReport(it)
                                            }
                                        )
                                    }
                                    WeatherReportUiState.Loading -> LoadingScreen()
                                    is WeatherReportUiState.Ready -> MainWeatherReportScreen(
                                        summary = state.detail,
                                        onSearchLocation = {
                                            weatherReportViewModel.loadWeatherReport(it)
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        weatherReportViewModel.loadWeatherReport()


    }
}
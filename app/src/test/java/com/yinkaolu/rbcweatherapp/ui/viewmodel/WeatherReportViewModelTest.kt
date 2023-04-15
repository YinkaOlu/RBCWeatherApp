package com.yinkaolu.rbcweatherapp.ui.viewmodel

import com.yinkaolu.rbcweatherapp.data.OpenWeatherRepository
import com.yinkaolu.rbcweatherapp.data.api.model.geo.GeoLocation
import com.yinkaolu.rbcweatherapp.data.api.model.weather.ForecastReport
import com.yinkaolu.rbcweatherapp.data.api.model.weather.WeatherReport
import com.yinkaolu.rbcweatherapp.ui.viewmodel.uistate.WeatherReportUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.mockito.Mockito.*

/**
 * A test watcher to set Main Coroutine Dispatcher to use the TestDispatcher for testing
 * (ie, wait for coroutine execution queue to complete before assertion)
 */
class CoroutineDispatchTestWatcher(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherReportViewModelTest {
    @get:Rule
    val mainDispatcherRule = CoroutineDispatchTestWatcher()

    var openWeatherRepository: OpenWeatherRepository = mock()

    lateinit var weatherReportViewModel: WeatherReportViewModel

    @Before
    fun setup() {
        weatherReportViewModel = WeatherReportViewModel(
            openWeatherRepository = openWeatherRepository
        )
    }

    @Test
    fun `On initial load, do not load location weather data without location permission`() =
        runTest {
            val state = weatherReportViewModel.weatherReportUiState

            assert(state.value == WeatherReportUiState.RequestLocationPermission)

            weatherReportViewModel.loadWeatherReport()

            advanceUntilIdle()

            verify(openWeatherRepository, never()).loadCurrentWeatherReport(
                anyString(),
                anyString()
            )
        }

    @Test
    fun `When local coordinates set, update UI state to Main Page`() = runTest {
        assert(
            weatherReportViewModel.weatherReportUiState.value
                    is WeatherReportUiState.RequestLocationPermission
        )

        `when`(openWeatherRepository.loadCurrentWeatherReport(anyString(), anyString())).thenReturn(
            WeatherReport(
                cityID = "Toronto"
            )
        )

        `when`(
            openWeatherRepository.findLocationByCoordinates(
                anyString(),
                anyString()
            )
        ).thenReturn(
            listOf(
                GeoLocation(name = "Toronto")
            )
        )

        // Permission Granted, set local coordinates
        weatherReportViewModel.setCurrentLocation(47.0, 90.0)

        assert(
            weatherReportViewModel.weatherReportUiState.value
                    is WeatherReportUiState.MainWeatherPage
        )
    }

    @Test
    fun `On network error, update UI state to Error`() = runTest {
        assert(
            weatherReportViewModel.weatherReportUiState.value
                    is WeatherReportUiState.RequestLocationPermission
        )

        `when`(openWeatherRepository.loadCurrentWeatherReport(anyString(), anyString()))
            .thenThrow(Exception("Network Error"))

        `when`(
            openWeatherRepository.findLocationByCoordinates(
                anyString(),
                anyString()
            )
        ).thenReturn(
            listOf(
                GeoLocation(name = "Toronto")
            )
        )

        // Permission Granted, set local coordinates
        weatherReportViewModel.setCurrentLocation(47.0, 90.0)

        assert(
            weatherReportViewModel.weatherReportUiState.value
                    is WeatherReportUiState.Error
        )
    }


    @Test
    fun `When load forecast data, update UI state to Forecast Page`() = runTest {
        `when`(openWeatherRepository.loadCurrentWeatherReport(anyString(), anyString()))
            .thenReturn(WeatherReport(cityID = "Toronto"))

        `when`(openWeatherRepository.loadForecastReport(anyString(), anyString()))
            .thenReturn(ForecastReport(list = emptyList(), count = 0))

        `when`(openWeatherRepository.findLocationByCoordinates(anyString(), anyString()))
            .thenReturn(listOf(GeoLocation(name = "Toronto")))

        `when`(openWeatherRepository.findLocation(anyString(), eq(null), eq(null)))
            .thenReturn(listOf(GeoLocation(name = "Toronto")))

        weatherReportViewModel.setCurrentLocation(49.0, 77.0)
        weatherReportViewModel.loadForcastReport()

        advanceUntilIdle()

        assert(
            weatherReportViewModel.weatherReportUiState.value
                    is WeatherReportUiState.ForcastDetailPage
        )
    }
}
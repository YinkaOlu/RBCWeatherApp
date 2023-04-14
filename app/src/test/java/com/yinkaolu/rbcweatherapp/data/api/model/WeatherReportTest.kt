package com.yinkaolu.rbcweatherapp.data.api.model

import com.google.gson.Gson
import com.google.gson.JsonParser
import org.junit.Test


class WeatherReportTest {
    private val gson = Gson()
    @Test
    fun fullWeather_conversion() {
        val parsedWeatherReport =
            gson.fromJson(SampleJSONString.FULL_WEATHER_REPORT, WeatherReport::class.java)

        parsedWeatherReport.apply {
            assert(cityID == "3163858")
            assert(cityName == "Zocca")

            assert(coordinates.latitude == 44.34)
            assert(coordinates.longitude == 10.99)

            assert(weather.size == 1)

            assert(weather[0].id == "501")
            assert(weather[0].main == "Rain")
            assert(weather[0].description == "moderate rain")
            assert(weather[0].icon == "10d")

            assert(mainWeather.temperature == 298.48)
            assert(mainWeather.feelsLikeTemperature == 298.74)
            assert(mainWeather.airPressure == 1015.0)
            assert(mainWeather.humidity == 64.0)
            assert(mainWeather.maxTemperature == 300.05)
            assert(mainWeather.minTemperature == 297.56)
            assert(mainWeather.seaLevel == 1015.0)
            assert(mainWeather.groundLevel == 933.0)

            assert(visibility == 10000.0)

            assert(windReport?.windSpeed == 0.62)
            assert(windReport?.windGust == 1.18)
            assert(windReport?.windDirectionDegree == 349.0)

            assert(rainReport?.rainOverLastHour == 3.16)
            assert(rainReport?.rainOverLastThreeHour == 3.17)

            assert(snowReport?.snowOverLastHour == 1.16)
            assert(snowReport?.snowOverLastThreeHour == 1.17)

            assert(cloudReport?.coverage == 100.0)
        }
    }
}
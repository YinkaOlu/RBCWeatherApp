package com.yinkaolu.rbcweatherapp.data.api.model

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yinkaolu.rbcweatherapp.data.api.model.geo.GeoLocation
import com.yinkaolu.rbcweatherapp.data.api.model.weather.ForecastReport
import com.yinkaolu.rbcweatherapp.data.api.model.weather.WeatherReport
import org.junit.Test
import java.io.BufferedReader
import java.io.FileReader


class DataModelTest {
    private val gson = Gson()

    private fun readJSON(fileName: String): String {
        val forcastFile = javaClass.classLoader?.getResource("$fileName.json")?.file

        val fileReader = FileReader(forcastFile)
        val bufferedReader = BufferedReader(fileReader)
        val stringBuilder = StringBuilder()
        var line = bufferedReader.readLine()
        while (line != null) {
            stringBuilder.append(line).append("\n")
            line = bufferedReader.readLine()
        }
        bufferedReader.close()
        return stringBuilder.toString()
    }

    @Test
    fun forcast_conversion() {
        val forcastReportString = readJSON("sample_forcast")
        val parsedForecastReport = gson.fromJson(forcastReportString, ForecastReport::class.java)

        parsedForecastReport.apply {
            assert(true)
        }
    }

    @Test
    fun geolocation_conversion() {
        val locationListType = object : TypeToken<List<GeoLocation>>() {}.type

        val fullLocationJsonString = readJSON("full_location")

        val parsedLocationResponse =
            gson.fromJson<List<GeoLocation>>(fullLocationJsonString, locationListType)

        parsedLocationResponse.apply {
            assert(parsedLocationResponse.size == 5)

            parsedLocationResponse[0].apply {
                assert(name == "London")
                assert(latitude == 51.5085)
                assert(longitude == -0.1257)
                assert(country == "GB")
            }
            parsedLocationResponse[1].apply {
                assert(name == "London")
                assert(latitude == 42.9834)
                assert(longitude == -81.233)
                assert(country == "CA")
            }
            parsedLocationResponse[2].apply {
                assert(name == "London")
                assert(latitude == 39.8865)
                assert(longitude == -83.4483)
                assert(country == "US")
                assert(state == "OH")
            }
        }
    }

    @Test
    fun fullWeather_conversion() {
        val fullWeatherJSONString = readJSON("full_weather")
        val parsedWeatherReport =
            gson.fromJson(fullWeatherJSONString, WeatherReport::class.java)

        parsedWeatherReport.apply {
            assert(cityID == "3163858")
            assert(cityName == "Zocca")

            assert(coordinates?.latitude == 44.34)
            assert(coordinates?.longitude == 10.99)

            assert(weather.size == 1)

            assert(weather[0].id == "501")
            assert(weather[0].main == "Rain")
            assert(weather[0].description == "moderate rain")
            assert(weather[0].icon == "10d")

            assert(mainWeather?.temperature == 298.48)
            assert(mainWeather?.feelsLikeTemperature == 298.74)
            assert(mainWeather?.airPressure == 1015.0)
            assert(mainWeather?.humidity == 64.0)
            assert(mainWeather?.maxTemperature == 300.05)
            assert(mainWeather?.minTemperature == 297.56)
            assert(mainWeather?.seaLevel == 1015.0)
            assert(mainWeather?.groundLevel == 933.0)

            assert(visibility == 10000.0)

            assert(windReport?.windSpeed == 0.62)
            assert(windReport?.windGust == 1.18)
            assert(windReport?.windDirectionDegree == 349.0)

            assert(rainReport?.rainOverLastHour == 3.16)

            assert(cloudReport?.coverage == 100.0)
        }
    }
}
package com.yinkaolu.rbcweatherapp.domain

import com.yinkaolu.rbcweatherapp.data.api.model.geo.GeoLocation
import com.yinkaolu.rbcweatherapp.data.api.model.weather.Forcast
import com.yinkaolu.rbcweatherapp.data.api.model.weather.ForecastReport
import com.yinkaolu.rbcweatherapp.data.api.model.weather.WeatherReport
import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.ForecastSummary
import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.LocationSummary
import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.WeatherSummary
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*

private val mainDateFormatter by lazy { SimpleDateFormat("EEEE, MMM dd", Locale.getDefault()) }
private val forecastDateFormatter by lazy { SimpleDateFormat("MMM dd (HH:mm)", Locale.getDefault()) }

private const val UNKNWON_CITY_FALLBACK = "unknown"
private const val UNKNOWN_TEMPERATURE_FALLBACK = "unknown"

fun GeoLocation?.toLocationSummary() = this?.let {
    LocationSummary(
        cityName = it.name ?: UNKNWON_CITY_FALLBACK,
        stateName = it.state,
        countryName = it.country
    )
}

fun WeatherReport.toWeatherSummary() = WeatherSummary(
    icon = weather.firstOrNull()?.icon.orEmpty(),
    weatherDescription = toQuickDescription(),
    temperature = convertKelvinToCelsius(mainWeather?.temperature)?.toCelsiusString() ?: UNKNOWN_TEMPERATURE_FALLBACK,
    dateString = this.atTime?.let {
        val date = Date(it.toLong() * 1000)
        mainDateFormatter.format(date)
    }.orEmpty()
)

fun ForecastReport.toForecastSummary() = ForecastSummary(
    weatherSummaries = list.map { forecast ->
        WeatherSummary(
            icon = forecast.weather.firstOrNull()?.icon.orEmpty(),
            weatherDescription = forecast.toFullDescription(),
            temperature = convertKelvinToCelsius(forecast.mainWeather?.temperature)?.toCelsiusString()
                ?: UNKNWON_CITY_FALLBACK,
            dateString = forecast.time?.let {
                val date = Date(it.toLong() * 1000)
                forecastDateFormatter.format(date)
            }.orEmpty()
        )
    }
)

fun convertKelvinToCelsius(kelvin: Double?): Double? =
    kelvin?.let {
        BigDecimal(it.minus(kelvinToCelsiusDif)).setScale(2, RoundingMode.CEILING).toDouble()
    }

fun Double.toCelsiusString(): String = "$this°C"

fun Forcast.toFullDescription(): String {
    var report = "- "
    report += weather
        .map { it.description }.joinToString("\n")

    report += "\n\n"

    mainWeather?.let {
        report += "- Feels like: ${convertKelvinToCelsius(it.feelsLikeTemperature)?.toCelsiusString()}\n\n"
        report += "- Humidity: ${it.humidity}%\n\n"
    }

    report += "- Cloud Visibility: ${visibility}\n\n"
    cloudReport?.let {
        report += "- Cloud Coverage: ${it.coverage}% \n\n"
    }

    report += "- Precipitation: ${precipitationProbability?.times(100)}%\n\n"
    rainReport?.let {
        report += "- Rain over last Hr: ${it.rainOverLastHour} m\n\n"
        report += "- Rain over last 3 Hrs: ${it.rainOverLastThreeHour} m\n\n"
    }

    snowReport?.let {
        report += "- Snow over last Hr: ${it.snowOverLastHour}\n\n"
        report += "- Snow over last 3 Hrs: ${it.snowOverLastThreeHour}\n\n"
    }

    windReport?.let {
        report += "- Wind Speed: ${it.windSpeed} m/s \n\n"
        report += "- Wind Direction: ${it.windDirectionDegree}°\n\n"
    }

    return report
}

fun WeatherReport.toQuickDescription(): String {
    var report = "- "
    report += weather
        .map { it.description }.joinToString(" / ")

    report += "\n\n"

    mainWeather?.let {
        report += "- Feels like: ${convertKelvinToCelsius(it.feelsLikeTemperature)?.toCelsiusString()}\n\n"
        report += "- Humidity: ${it.humidity}%\n\n"
    }

    return report
}

private const val kelvinToCelsiusDif = 273.15
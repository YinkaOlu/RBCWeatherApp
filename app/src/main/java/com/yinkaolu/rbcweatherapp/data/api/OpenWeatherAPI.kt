package com.yinkaolu.rbcweatherapp.data.api

import com.yinkaolu.rbcweatherapp.data.api.model.geo.GeoLocationList
import com.yinkaolu.rbcweatherapp.data.api.model.weather.ForecastReport
import com.yinkaolu.rbcweatherapp.data.api.model.weather.WeatherReport
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherAPI {
    @GET("data/2.5/forecast")
    fun getForcast(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("appid") apiKey: String
    ): Call<ForecastReport>

    @GET("data/2.5/weather")
    fun getWeatherDetails(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("appid") apiKey: String
    ): Call<WeatherReport>

    @GET("geo/1.0/direct")
    fun findGeoLocation(
        @Query("q") cityStateCountryQuery: String,
        @Query("appid") apiKey: String
    ): Call<GeoLocationList>

    @GET("geo/1.0/reverse")
    fun findLocationCoordinates(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") apiKey: String
    ): Call<GeoLocationList>
}
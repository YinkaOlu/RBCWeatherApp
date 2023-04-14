package com.yinkaolu.rbcweatherapp.data.api

import com.yinkaolu.rbcweatherapp.data.api.geo.GeoLocationList
import com.yinkaolu.rbcweatherapp.data.api.weather.WeatherReport
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherAPI {
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
}
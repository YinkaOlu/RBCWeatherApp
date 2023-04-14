package com.yinkaolu.rbcweatherapp.data.api

import com.yinkaolu.rbcweatherapp.data.api.model.WeatherReport
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("data/2.5/weather")
    fun getWeatherDetails(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("appid") apiKey: String
    ): Call<WeatherReport>
}
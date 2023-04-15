package com.yinkaolu.rbcweatherapp.data.api.model.weather

import com.google.gson.annotations.SerializedName

data class ForcastReport(
    @SerializedName("cnt")
    val count: Int,
    @SerializedName("list")
    val list: List<Forcast>
)

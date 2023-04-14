package com.yinkaolu.rbcweatherapp.data.api.model

import com.google.gson.annotations.SerializedName

data class CloudReport(
    @SerializedName("all")
    val coverage: Double? = null
)
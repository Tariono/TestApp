package com.nickcorban.testapp.data.database.entry

import com.google.gson.annotations.SerializedName


data class Geo(
    @SerializedName("lat")
    val lat: String,
    @SerializedName("lng")
    val lng: String
)
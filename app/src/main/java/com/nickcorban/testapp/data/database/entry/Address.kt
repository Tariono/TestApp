package com.nickcorban.testapp.data.database.entry

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName


data class Address(
    @SerializedName("city")
    val city: String,
    @SerializedName("geo")
    @Embedded(prefix = "geo_")
    val geo: Geo,
    @SerializedName("street")
    val street: String,
    @SerializedName("suite")
    val suite: String,
    @SerializedName("zipcode")
    val zipcode: String
)
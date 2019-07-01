package com.nickcorban.testapp.data.database.output_entries

import androidx.room.ColumnInfo

data class ContactDetail(
    @ColumnInfo(name = "address_city")
    val city : String,
    @ColumnInfo(name = "address_geo_lat")
    val lat : String,
    @ColumnInfo(name = "address_geo_lng")
    val lng : String,
    @ColumnInfo(name = "address_street")
    val street : String,
    @ColumnInfo(name = "address_suite")
    val suite : String,
    @ColumnInfo(name = "address_zipcode")
    val zipcode : String,
    @ColumnInfo(name = "company_bs")
    val bs : String,
    @ColumnInfo(name = "company_catchPhrase")
    val catchphrase : String,
    @ColumnInfo(name = "company_name")
    val companyName : String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "website")
    val website: String
)
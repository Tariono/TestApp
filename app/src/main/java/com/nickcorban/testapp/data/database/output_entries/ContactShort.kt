package com.nickcorban.testapp.data.database.output_entries

import androidx.room.ColumnInfo

data class ContactShort (

    @ColumnInfo(name = "id")
    val id : Int,
    @ColumnInfo(name = "name")
    val name : String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "website")
    val companyName : String
)

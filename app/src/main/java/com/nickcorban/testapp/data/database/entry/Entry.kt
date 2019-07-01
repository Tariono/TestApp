package com.nickcorban.testapp.data.database.entry

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "contactsTable")
data class Entry(
    @SerializedName("address")
    @Embedded(prefix = "address_")
    val address: Address,
    @SerializedName("company")
    @Embedded(prefix = "company_")
    val company: Company,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    @PrimaryKey
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("website")
    val website: String
)
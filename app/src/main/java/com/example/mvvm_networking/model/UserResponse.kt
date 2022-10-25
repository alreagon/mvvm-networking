package com.example.mvvm_networking.model


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class UserResponse(
    @SerializedName("address")
    @Embedded(prefix = "user_")
    val address: Address?,

    @SerializedName("company")
    @Embedded(prefix = "user_")
    val company: Company,

    @SerializedName("email")
    val email: String,

    @PrimaryKey
    @SerializedName("id")
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
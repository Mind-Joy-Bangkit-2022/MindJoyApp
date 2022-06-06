package com.example.mindjoy.network

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegisterUser(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("username")
    val username: String,

    @field:SerializedName("password")
    val password: String,
) : Parcelable

data class RegisterUserResponse(
    @field:SerializedName("message")
    val message: String
)

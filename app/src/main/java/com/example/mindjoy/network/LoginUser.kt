package com.example.mindjoy.network

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginUser(
    @field:SerializedName("username")
    val username: String,

    @field:SerializedName("password")
    val password: String
) : Parcelable

data class LoginUserResponse(
    @field:SerializedName("message")
    val message: String
)

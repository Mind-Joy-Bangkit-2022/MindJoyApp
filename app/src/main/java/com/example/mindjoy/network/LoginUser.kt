package com.example.mindjoy.network

import com.google.gson.annotations.SerializedName

data class LoginUser(
    @field:SerializedName("username")
    val username: String,

    @field:SerializedName("password")
    val password: String
)

data class LoginUserResponse(
    @field:SerializedName("message")
    val message: String
)

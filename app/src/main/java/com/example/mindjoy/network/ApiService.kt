package com.example.mindjoy.network

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("regist")
    fun userRegister(
        @Body register: RegisterUser
    ): Call<RegisterUserResponse>
}
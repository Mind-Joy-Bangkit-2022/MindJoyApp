package com.example.mindjoy.network

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("register")
    fun userRegister(
        @Body register: RegisterUser
    ): Call<RegisterUserResponse>

    @POST("login")
    fun userLogin(
        @Body login: LoginUser
    ): Call<LoginUserResponse>

    @Multipart
    @POST("emotion")
    fun emotion(
        @Part img: MultipartBody.Part
    ): Call<EmotionResult>
}
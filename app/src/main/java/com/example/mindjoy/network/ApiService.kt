package com.example.mindjoy.network

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

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

    @POST("mentalhealth")
    fun mentalHealth(
        @Body mentalHealth: MentalHealthData
    ) : Call<MentalHealthResponse>
}
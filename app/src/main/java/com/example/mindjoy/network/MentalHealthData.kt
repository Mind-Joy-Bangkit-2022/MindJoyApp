package com.example.mindjoy.network

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MentalHealthData(
    @field:SerializedName("gender")
    val gender: String,

    @field:SerializedName("age")
    val age: String,

    @field:SerializedName("feeling")
    val feeling: String,

    @field:SerializedName("sadness")
    val sadness: String,

    @field:SerializedName("time")
    val time: String,

    @field:SerializedName("interest")
    val interest: String,

    @field:SerializedName("confident")
    val confident: String,

    @field:SerializedName("supported")
    val supported: String,

    @field:SerializedName("things")
    val things: String,

    @field:SerializedName("medical")
    val medical: String,

    @field:SerializedName("substance")
    val substance: String,

    @field:SerializedName("hours")
    val hours: String,

    @field:SerializedName("appointment")
    val appointment: String,

    @field:SerializedName("offended")
    val offended: String,

    @field:SerializedName("vulnerable")
    val vulnerable: String,

    @field:SerializedName("comfortable")
    val comfortable: String
) : Parcelable

@Parcelize
data class MentalHealthResponse(
    @field:SerializedName("result")
    val result: String,

    @field:SerializedName("score")
    val score: Int
) : Parcelable

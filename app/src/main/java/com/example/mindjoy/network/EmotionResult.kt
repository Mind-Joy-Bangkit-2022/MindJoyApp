package com.example.mindjoy.network

import com.google.gson.annotations.SerializedName

data class EmotionResult(
    @field:SerializedName("result")
    val result: String
)

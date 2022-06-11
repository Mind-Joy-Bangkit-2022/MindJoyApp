package com.example.mindjoy.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mindjoy.network.ApiConfig
import com.example.mindjoy.network.EmotionResult
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CameraResultViewModel : ViewModel() {

    private val _response = MutableLiveData<EmotionResult>()
    val response: LiveData<EmotionResult> = _response

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isSuccessful = MutableLiveData<Boolean>()
    val isSuccessful: LiveData<Boolean> = _isSuccessful

    fun updateSuccessfulValue(newState: Boolean){
        _isSuccessful.value = newState
    }

    fun setExpression(imageMultipart: MultipartBody.Part){
        _isLoading.value = true

        val client = ApiConfig.getApiService().emotion(imageMultipart)
        client.enqueue(object : Callback<EmotionResult> {
            override fun onResponse(call: Call<EmotionResult>, response: Response<EmotionResult>) {
                _isSuccessful.postValue(true)
                _response.postValue(response.body())
                _isLoading.value = false
            }

            override fun onFailure(call: Call<EmotionResult>, t: Throwable) {
                _isSuccessful.postValue(false)
                _isLoading.value = false
            }
        })
    }
}
package com.example.mindjoy.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mindjoy.network.*
import retrofit2.Call
import retrofit2.Response
import java.net.SocketTimeoutException

class QuestionViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isSuccessful = MutableLiveData<Boolean>()
    val isSuccessful: LiveData<Boolean> = _isSuccessful

//    private val _isFailed = MutableLiveData<Boolean>()
//    val isFailed: LiveData<Boolean> = _isFailed

    private val _response = MutableLiveData<String>()
    val response: LiveData<String> = _response

    fun updateSuccessfulValue(newState: Boolean) {
        _isSuccessful.value = newState
    }

    fun setQuestion(data: MentalHealthData){
        _isLoading.value = true

        val client = ApiConfig.getApiService().mentalHealth(data)
        client.enqueue(object : retrofit2.Callback<MentalHealthResponse> {
            override fun onResponse(call: Call<MentalHealthResponse>, response: Response<MentalHealthResponse>) {
                if (response.isSuccessful){
                    _isSuccessful.value = true
                    _isLoading.value = false
                    _response.postValue(response.body()?.result)
                } else {
                    _isSuccessful.value = false
                    _isLoading.value = false
                    _response.postValue(response.body()?.result)
                }
            }

            override fun onFailure(call: Call<MentalHealthResponse>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message}")
                _isSuccessful.value = false
                _isLoading.value = false
            }
        })
    }

}
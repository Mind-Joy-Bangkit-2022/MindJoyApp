package com.example.mindjoy.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mindjoy.network.ApiConfig
import com.example.mindjoy.network.RegisterUser
import com.example.mindjoy.network.RegisterUserResponse
import retrofit2.Call
import retrofit2.Response

class RegisterViewModel: ViewModel() {

    private val _isSuccessful = MutableLiveData<Boolean>()
    val isSuccessful: LiveData<Boolean> = _isSuccessful

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun setRegisterUser(registerUser: RegisterUser){
        _isLoading.value = true

        val client = ApiConfig.getApiService().userRegister(registerUser)
        client.enqueue(object : retrofit2.Callback<RegisterUserResponse> {
            override fun onResponse(call: Call<RegisterUserResponse>, response: Response<RegisterUserResponse>) {
                if (response.isSuccessful){
                      _isSuccessful.value = true
                    _isLoading.value = false
                } else {
                    _isSuccessful.value = false
                    _isLoading.value = false
                }
            }

            override fun onFailure(call: Call<RegisterUserResponse>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message}")
            }
        })
    }
}
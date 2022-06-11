package com.example.mindjoy.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mindjoy.network.ApiConfig
import com.example.mindjoy.network.LoginUser
import com.example.mindjoy.network.LoginUserResponse
import retrofit2.Call
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val _isSuccessful = MutableLiveData<Boolean>()
    val isSuccessful: LiveData<Boolean> = _isSuccessful

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun setLoginUser(loginUser: LoginUser){
        _isLoading.value = true

        val client = ApiConfig.getApiService().userLogin(loginUser)
        client.enqueue(object : retrofit2.Callback<LoginUserResponse> {
            override fun onResponse(call: Call<LoginUserResponse>, response: Response<LoginUserResponse>) {
                if (response.isSuccessful){
                    _isSuccessful.value = true
                    _isLoading.value = false
                } else {
                    _isSuccessful.value = false
                    _isLoading.value = false
                }
            }

            override fun onFailure(call: Call<LoginUserResponse>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message}")
                _isLoading.value = false
            }
        })
    }
}
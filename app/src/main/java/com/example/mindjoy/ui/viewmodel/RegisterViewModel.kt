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
import javax.security.auth.callback.Callback

class RegisterViewModel: ViewModel() {

    private val _isSuccessful = MutableLiveData<Boolean>()
    val isSuccessful: LiveData<Boolean> = _isSuccessful

    fun setRegisterUser(registerUser: RegisterUser){
        val client = ApiConfig.getApiService().userRegister(registerUser)
        client.enqueue(object : retrofit2.Callback<RegisterUserResponse> {
            override fun onResponse(call: Call<RegisterUserResponse>, response: Response<RegisterUserResponse>) {
                if (response.isSuccessful){
                    _isSuccessful.value = true
                } else {
                    _isSuccessful.value = false
                }
            }

            override fun onFailure(call: Call<RegisterUserResponse>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message}")
            }
        })
    }
}
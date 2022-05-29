package com.example.mindjoy.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import com.example.mindjoy.R
import com.example.mindjoy.customview.ClearImgEditText
import com.example.mindjoy.customview.PasswordEditText
import com.example.mindjoy.databinding.ActivityLoginBinding
import com.example.mindjoy.ui.MainActivity
import com.example.mindjoy.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsername: ClearImgEditText
    private lateinit var edPw: PasswordEditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignup: Button
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        etUsername = findViewById(R.id.et_username)
        edPw = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_login)
        btnSignup = findViewById(R.id.btn_signup)

        onClickListener()
    }

    private fun onClickListener(){
        btnLogin.setOnClickListener{
            movetoHome()
        }

        btnSignup.setOnClickListener{
            moveToRegister()
        }
    }

    private fun movetoHome(){
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        },500)
    }

    private fun moveToRegister(){
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        },500)
    }
}
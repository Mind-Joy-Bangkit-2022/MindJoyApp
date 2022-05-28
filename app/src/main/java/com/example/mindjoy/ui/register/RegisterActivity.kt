package com.example.mindjoy.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import com.example.mindjoy.R
import com.example.mindjoy.customview.ClearImgEditText
import com.example.mindjoy.customview.PasswordEditText
import com.example.mindjoy.databinding.ActivityRegisterBinding
import com.example.mindjoy.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var etName: ClearImgEditText
    private lateinit var etUsername: ClearImgEditText
    private lateinit var edPw: PasswordEditText
    private lateinit var btnSignup: Button
    private lateinit var tvHaveAccount: TextView
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        etName = findViewById(R.id.et_name)
        etUsername = findViewById(R.id.et_username)
        edPw = findViewById(R.id.et_password)
        btnSignup = findViewById(R.id.btn_signup)
        tvHaveAccount = findViewById(R.id.tv_have_account)

        onClickListener()
    }

    private fun onClickListener(){
        btnSignup.setOnClickListener{
            moveToLogin()
        }

        tvHaveAccount.setOnClickListener {
            moveToLogin()
        }
    }

    private fun moveToLogin(){
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        },500)

    }
}
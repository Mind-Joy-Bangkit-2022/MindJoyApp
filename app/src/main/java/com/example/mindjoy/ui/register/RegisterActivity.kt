package com.example.mindjoy.ui.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mindjoy.R
import com.example.mindjoy.customview.ClearImgEditText
import com.example.mindjoy.customview.PasswordEditText
import com.example.mindjoy.databinding.ActivityRegisterBinding
import com.example.mindjoy.network.RegisterUser
import com.example.mindjoy.ui.login.LoginActivity
import com.example.mindjoy.ui.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var etName: ClearImgEditText
    private lateinit var etUsername: ClearImgEditText
    private lateinit var edPw: PasswordEditText
    private lateinit var btnSignup: Button
    private lateinit var tvHaveAccount: TextView
    private lateinit var binding: ActivityRegisterBinding

    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        etName = findViewById(R.id.et_name)
        etUsername = findViewById(R.id.et_username)
        edPw = findViewById(R.id.et_password)
        btnSignup = findViewById(R.id.btn_signup)
        tvHaveAccount = findViewById(R.id.tv_have_account)

        binding.progressBar.visibility = View.INVISIBLE

        onClickListener()

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            RegisterViewModel::class.java
        )

        viewModel.isSuccessful.observe(this) {
            if (it) {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                moveToLogin()
            }
        }

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun onClickListener(){
        btnSignup.setOnClickListener{
            val name = etName.text.toString().trim()
            val username = etUsername.text.toString().trim()
            val password = edPw.text.toString().trim()
            val registerUser = RegisterUser(name, username, password)

            viewModel.setRegisterUser(registerUser)
        }

        tvHaveAccount.setOnClickListener {
            moveToLogin()
        }
    }

    private fun moveToLogin(){
        Intent(this, LoginActivity::class.java).also {
            startActivity(it)
        }
        finish()
    }

    private fun showLoading(state: Boolean){
        binding.progressBar.visibility = if(state) View.VISIBLE else View.GONE
    }
}
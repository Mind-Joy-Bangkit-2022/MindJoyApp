package com.example.mindjoy.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mindjoy.R
import com.example.mindjoy.customview.ClearImgEditText
import com.example.mindjoy.customview.PasswordEditText
import com.example.mindjoy.databinding.ActivityLoginBinding
import com.example.mindjoy.network.LoginUser
import com.example.mindjoy.ui.MainActivity
import com.example.mindjoy.ui.helper.Session
import com.example.mindjoy.ui.helper.UserDataPreferences
import com.example.mindjoy.ui.register.RegisterActivity
import com.example.mindjoy.ui.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsername: ClearImgEditText
    private lateinit var edPw: PasswordEditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignup: Button
    private lateinit var binding: ActivityLoginBinding

    private lateinit var viewModel: LoginViewModel
    private lateinit var session: Session
    private lateinit var userPref: UserDataPreferences

    private lateinit var loginUser: LoginUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        etUsername = findViewById(R.id.et_username)
        edPw = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_login)
        btnSignup = findViewById(R.id.btn_signup)

        etUsername.addTextChangedListener(loginTextWatcher)
        edPw.addTextChangedListener(loginTextWatcher)

        binding.progressBar.visibility = View.INVISIBLE

        onClickListener()

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[LoginViewModel::class.java]

        session = Session(this)
        userPref = UserDataPreferences(this)

        viewModel.response.observe(this) {
            if (it == "Login Sukses") {
                session.saveLogin(true)
                viewModel.updateSuccessfulValue(false)
                moveToHome()
            }
            else {
                Toast.makeText(this, "Wrong username or password!", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private val loginTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val username = etUsername.text.toString().trim()
            val password = edPw.text.toString().trim()

            btnLogin.isEnabled = username.isNotEmpty() && password.length > 5
        }

        override fun afterTextChanged(p0: Editable?) {}
    }

    private fun onClickListener(){
        btnLogin.setOnClickListener{
            val username = etUsername.text.toString().trim()
            val password = edPw.text.toString().trim()
            loginUser = LoginUser(username, password)

            viewModel.setLoginUser(loginUser)
            userPref.saveName(username)
        }

        btnSignup.setOnClickListener{
            moveToRegister()
        }
    }

    override fun onStart() {
        super.onStart()
        if (session.getLogin()) {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
            finish()
        }
    }

    private fun moveToHome(){
        Intent(this, MainActivity::class.java).also {
            startActivity(it)
        }
        finish()
    }

    private fun moveToRegister(){
        Intent(this, RegisterActivity::class.java).also {
            startActivity(it)
        }
    }

    private fun showLoading(state: Boolean){
        binding.progressBar.visibility = if(state) View.VISIBLE else View.GONE
    }
}
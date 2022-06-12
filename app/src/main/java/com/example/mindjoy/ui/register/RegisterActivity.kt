package com.example.mindjoy.ui.register

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

    private var registerUser: RegisterUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        etName = findViewById(R.id.et_name)
        etUsername = findViewById(R.id.et_username)
        edPw = findViewById(R.id.et_password)
        btnSignup = findViewById(R.id.btn_signup)
        tvHaveAccount = findViewById(R.id.tv_have_account)

        etName.addTextChangedListener(registerTextWatcher)
        etUsername.addTextChangedListener(registerTextWatcher)
        edPw.addTextChangedListener(registerTextWatcher)

        binding.progressBar.visibility = View.INVISIBLE

        onClickListener()

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[RegisterViewModel::class.java]

        viewModel.isSuccessful.observe(this) {
            if (it) {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                moveToLogin()
            } else {
                Toast.makeText(this, "Username or password is already taken", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private val registerTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val name = etName.text.toString().trim()
            val username = etUsername.text.toString().trim()
            val password = edPw.text.toString().trim()

            btnSignup.isEnabled = name.isNotEmpty() && username.isNotEmpty() && password.length > 5
        }

        override fun afterTextChanged(p0: Editable?) {}
    }



    private fun onClickListener(){
        btnSignup.setOnClickListener{
            val name = etName.text.toString().trim()
            val username = etUsername.text.toString().trim()
            val password = edPw.text.toString().trim()
            registerUser = RegisterUser(name, username, password)

            viewModel.setRegisterUser(registerUser!!)
        }

        tvHaveAccount.setOnClickListener {
            moveToLogin()
        }
    }

    private fun moveToLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showLoading(state: Boolean){
        binding.progressBar.visibility = if(state) View.VISIBLE else View.GONE
    }
}
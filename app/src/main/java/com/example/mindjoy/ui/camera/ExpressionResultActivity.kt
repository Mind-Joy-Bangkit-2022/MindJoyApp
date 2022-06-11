package com.example.mindjoy.ui.camera

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.mindjoy.R
import com.example.mindjoy.databinding.ActivityExpressionResultBinding
import com.example.mindjoy.ui.MainActivity
import com.example.mindjoy.ui.helper.Session
import com.example.mindjoy.ui.helper.UserDataPreferences
import com.example.mindjoy.ui.viewmodel.SharedViewModel

class ExpressionResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExpressionResultBinding

    private val viewModel: SharedViewModel by viewModels()

    private lateinit var userPref: UserDataPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExpressionResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userPref = UserDataPreferences(this)

        val status = intent.getStringExtra("expressionStatus")

        when (status) {
            "Happy" -> {
                binding.expressionEmoticon.setImageResource(R.drawable.happy_face)
                userPref.saveExpression("Happy")
            }
            "Sad" -> {
                binding.expressionEmoticon.setImageResource(R.drawable.sad_face)
                binding.expressionStatus.text = "Sad"
                userPref.saveExpression("Sad")
            }
            else -> {
                binding.expressionStatus.text = "neutral"
                userPref.saveExpression("Neutral")
            }
        }

        binding.btnDetectAgain.setOnClickListener {
            Intent(this, CameraActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        binding.btnBackToHomeExpression.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }
    }
}
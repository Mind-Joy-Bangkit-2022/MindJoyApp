package com.example.mindjoy.ui.camera

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mindjoy.R
import com.example.mindjoy.databinding.ActivityExpressionResultBinding
import com.example.mindjoy.ui.MainActivity

class ExpressionResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExpressionResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExpressionResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        when (intent.getStringExtra("expressionStatus")) {
            "Happy" -> {
                binding.expressionEmoticon.setImageResource(R.drawable.happy_face)
            }
            "Sad" -> {
                binding.expressionEmoticon.setImageResource(R.drawable.sad_face)
                binding.expressionStatus.text = "Sad"
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
        }
    }
}
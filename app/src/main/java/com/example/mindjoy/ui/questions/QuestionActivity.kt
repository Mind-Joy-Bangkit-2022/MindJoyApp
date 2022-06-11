package com.example.mindjoy.ui.questions

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mindjoy.R
import com.example.mindjoy.databinding.ActivityQuestionBinding
import com.example.mindjoy.ui.MainActivity

class QuestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionBinding
    private lateinit var tvBack: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tvBack = findViewById(R.id.tv_back_button_question)
        tvBack.setOnClickListener {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            },300)
        }

        val ansGender = resources.getStringArray(R.array.answer1)
        val adapterGender = ArrayAdapter(this, R.layout.list_answer, ansGender)
        binding.answer1.setAdapter(adapterGender)

        val ansAge = resources.getStringArray(R.array.answer2)
        val adapterAge = ArrayAdapter(this, R.layout.list_answer, ansAge)
        binding.answer2.setAdapter(adapterAge)

        val ansFeel = resources.getStringArray(R.array.answer3)
        val adapterFeel = ArrayAdapter(this, R.layout.list_answer, ansFeel)
        binding.answer3.setAdapter(adapterFeel)

        val ansSadness = resources.getStringArray(R.array.answer4)
        val adapterSadness = ArrayAdapter(this, R.layout.list_answer, ansSadness)
        binding.answer4.setAdapter(adapterSadness)

        val ansTime = resources.getStringArray(R.array.answer5)
        val adapterTime = ArrayAdapter(this, R.layout.list_answer, ansTime)
        binding.answer5.setAdapter(adapterTime)

        val ansEnjoy = resources.getStringArray(R.array.answer6)
        val adapterEnjoy = ArrayAdapter(this, R.layout.list_answer, ansEnjoy)
        binding.answer6.setAdapter(adapterEnjoy)

        val ansConfident = resources.getStringArray(R.array.answer7)
        val adapterConfident = ArrayAdapter(this, R.layout.list_answer, ansConfident)
        binding.answer7.setAdapter(adapterConfident)

        val ansSupport = resources.getStringArray(R.array.answer8)
        val adapterSupport = ArrayAdapter(this, R.layout.list_answer, ansSupport)
        binding.answer8.setAdapter(adapterSupport)

        val ansFrequent = resources.getStringArray(R.array.answer9)
        val adapterFrequent = ArrayAdapter(this, R.layout.list_answer, ansFrequent)
        binding.answer9.setAdapter(adapterFrequent)

        val ansMedical = resources.getStringArray(R.array.answer10)
        val adapterMedical = ArrayAdapter(this, R.layout.list_answer, ansMedical)
        binding.answer10.setAdapter(adapterMedical)

        val ansSubstance = resources.getStringArray(R.array.answer11)
        val adapterSubstance = ArrayAdapter(this, R.layout.list_answer, ansSubstance)
        binding.answer11.setAdapter(adapterSubstance)

        val ansWatch = resources.getStringArray(R.array.answer12)
        val adapterWatch = ArrayAdapter(this, R.layout.list_answer, ansWatch)
        binding.answer12.setAdapter(adapterWatch)

        val ansAppoint = resources.getStringArray(R.array.answer13)
        val adapterAppoint = ArrayAdapter(this, R.layout.list_answer, ansAppoint)
        binding.answer13.setAdapter(adapterAppoint)

        val ansOffend = resources.getStringArray(R.array.answer14)
        val adapterOffend = ArrayAdapter(this, R.layout.list_answer, ansOffend)
        binding.answer14.setAdapter(adapterOffend)

        val ansAlone = resources.getStringArray(R.array.answer15)
        val adapterAlone = ArrayAdapter(this, R.layout.list_answer, ansAlone)
        binding.answer15.setAdapter(adapterAlone)

        val ansComfort = resources.getStringArray(R.array.answer16)
        val adapterComfort = ArrayAdapter(this, R.layout.list_answer, ansComfort)
        binding.answer16.setAdapter(adapterComfort)

    }
}
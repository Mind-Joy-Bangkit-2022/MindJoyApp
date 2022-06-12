package com.example.mindjoy.ui.questions

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mindjoy.R
import com.example.mindjoy.databinding.ActivityQuestionBinding
import com.example.mindjoy.network.MentalHealthData
import com.example.mindjoy.ui.MainActivity
import com.example.mindjoy.ui.camera.ExpressionResultActivity
import com.example.mindjoy.ui.viewmodel.QuestionViewModel

class QuestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionBinding
    private lateinit var tvBack: TextView
    private lateinit var viewModel: QuestionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[QuestionViewModel::class.java]

        binding.progressBar?.visibility = View.GONE

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

        tvBack = findViewById(R.id.tv_back_button_question)
        tvBack.setOnClickListener {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, 300)
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

        binding.btnSubmitSurvey.setOnClickListener {
            var genderChoice = binding.answer1.text.toString()
            when (genderChoice) {
                "Female" -> genderChoice = 1.toString()
                "Male" -> genderChoice = 0.toString()
                "Prefer not to say" -> genderChoice = 2.toString()
            }

            var ageChoice = binding.answer2.text.toString()
            when (ageChoice) {
                "No" -> ageChoice = 0.toString()
                "Yes" -> ageChoice = 1.toString()
            }

            var feelChoice = binding.answer3.text.toString()
            when (feelChoice) {
                "Fine" -> feelChoice = 0.toString()
                "Good" -> feelChoice = 1.toString()
                "Sad" -> feelChoice = 2.toString()
                "Depressed" -> feelChoice = 3.toString()
            }

            var sadnessChoice = binding.answer4.text.toString()
            when (sadnessChoice) {
                "For some time" -> sadnessChoice = 1.toString()
                "Significant time" -> sadnessChoice = 2.toString()
                "Not sad" -> sadnessChoice = 0.toString()
                "Long time" -> sadnessChoice = 3.toString()
            }

            var timeChoice = binding.answer5.text.toString()
            when (timeChoice) {
                "Evening" -> timeChoice = 2.toString()
                "Morning" -> timeChoice = 0.toString()
                "Afternoon" -> timeChoice = 1.toString()
            }

            var enjoyChoice = binding.answer6.text.toString()
            when (enjoyChoice) {
                "Very Often" -> enjoyChoice = 3.toString()
                "Sometimes" -> enjoyChoice = 1.toString()
                "Never" -> enjoyChoice = 0.toString()
                "Often" -> enjoyChoice = 2.toString()
            }

            val confidentChoice = binding.answer7.text.toString()

            var supportChoice = binding.answer8.text.toString()
            when (supportChoice) {
                "Highly supportive" -> supportChoice = 0.toString()
                "Little bit" -> supportChoice = 2.toString()
                "Satisfactory" -> supportChoice = 1.toString()
                "Not at all" -> supportChoice = 3.toString()
            }

            var frequentChoice = binding.answer9.text.toString()
            when (frequentChoice) {
                "Sometimes" -> frequentChoice = 2.toString()
                "Never" -> frequentChoice = 3.toString()
                "Often" -> frequentChoice = 1.toString()
                "Very Often" -> frequentChoice = 0.toString()
            }

            var medicalChoice = binding.answer10.text.toString()
            when (medicalChoice) {
                "Not so easy" -> medicalChoice = 2.toString()
                "Very easy" -> medicalChoice = 0.toString()
                "Difficult" -> medicalChoice = 3.toString()
                "Easy" -> medicalChoice = 1.toString()
            }

            var substanceChoice = binding.answer11.text.toString()
            when (substanceChoice) {
                "Never" -> substanceChoice = 0.toString()
                "Often" -> substanceChoice = 2.toString()
                "Sometimes" -> substanceChoice = 1.toString()
                "Very Often" -> substanceChoice = 3.toString()
            }

            var watchChoice = binding.answer12.text.toString()
            when (watchChoice) {
                "2 to 5 hours" -> watchChoice = 1.toString()
                "More than 10 hours" -> watchChoice = 3.toString()
                "5 to 10 hours" -> watchChoice = 2.toString()
                "1 to 2 hours" -> watchChoice = 0.toString()
            }

            val appointChoice = binding.answer13.text.toString()

            var offendChoice = binding.answer14.text.toString()
            when (offendChoice) {
                "1" -> offendChoice = 1.toString()
                "2" -> offendChoice = 2.toString()
                "3" -> offendChoice = 3.toString()
                "4" -> offendChoice = 0.toString()
            }

            val aloneChoice = binding.answer15.text.toString()
            val comfortChoice = binding.answer16.text.toString()

            val data = MentalHealthData(
                genderChoice,
                ageChoice,
                feelChoice,
                sadnessChoice,
                timeChoice,
                enjoyChoice,
                confidentChoice,
                supportChoice,
                frequentChoice,
                medicalChoice,
                substanceChoice,
                watchChoice,
                appointChoice,
                offendChoice,
                aloneChoice,
                comfortChoice,
            )

            viewModel.setQuestion(data)
            viewModel.updateSuccessfulValue(false)
            viewModel.isSuccessful.observe(this) { success ->
                if (success) {
                    viewModel.response.observe(this) {
                        val response = it
                        val intent = Intent(this, QuestionResultActivity::class.java)
                        intent.putExtra("mentalHealthStatus", response)
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                        startActivity(intent)
                    }
                    viewModel.updateSuccessfulValue(false)
                } else {
//                    Toast.makeText(this, "Something went wrong, please try again!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showLoading(state: Boolean){
        binding.progressBar?.visibility = if(state) View.VISIBLE else View.GONE
    }
}

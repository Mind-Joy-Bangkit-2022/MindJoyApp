package com.example.mindjoy.ui.questions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mindjoy.R
import com.example.mindjoy.databinding.ActivityQuestionResultBinding
import com.example.mindjoy.ui.helper.UserDataPreferences
import com.example.mindjoy.ui.viewmodel.QuestionViewModel

class QuestionResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuestionResultBinding
    private lateinit var userPref: UserDataPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userPref = UserDataPreferences(this)

        val status = intent.getStringExtra("mentalHealthStatus")

        binding.mentalHealthQuotes.text = randomQuote()

        when (status) {
            "Tidak Butuh Penanganan" -> {
                binding.mentalHealthEmoticon.setImageResource(R.drawable.happy_face)
                userPref.saveMentalHealth("Good")
            }
            "Butuh Penanganan" -> {
                binding.mentalHealthEmoticon.setImageResource(R.drawable.sad_face)
                binding.mentalHealthStatus.text = "Depressed"
                userPref.saveMentalHealth("Depressed")
            }
        }
    }

    private fun randomQuote(): String{
        val quotes = arrayOf("Don't compare yourself with other people; compare yourself with who you were yesterday. \n" +
                "Jordan Peterson", "A failure is not always a mistake, it may simply be the best one can do under the circumstances. The real mistake is to stop trying. \n" +
                "B. F. Skinner" , "Sometimes you make the right decision, sometimes you make the decision right. \n" +
                "Phil McGraw", "The most beautiful people we have known are those who have known defeat, known suffering, known struggle, known loss, and have found their way out of those depths. \n" +
                "Elisabeth Kubler-Ross", "The first step toward change is awareness. The second step is acceptance. \n" +
                "Nathaniel Branden", "Well-being cannot exist just in your own head. Well-being is a combination of feeling good as well as actually having meaning, good relationships and accomplishment. \n" +
                "Martin Seligman", "I was always looking outside myself for strength and confidence but it comes from within. It is there all the time. \n" +
                "Anna Freud", "Humans are social beings, and we are happier, and better, when connected to others.\n" +
                "Paul Bloom", "Everything has seasons, and we have to be able to recognize when something's time has passed and be able to move into the next season. Everything that is alive requires pruning as well, which is a great metaphor for endings.\n" +
                "Henry Cloud", "I am not in this world to live up to other people's expectations, nor do I feel that the world must live up to mine. \n" +
                "Fritz Perls", "When you don't manage your life well, you become angry and frustrated as things don't go as intended, and our bad mood is a sign showing we were not able to resolve the conflict. \n" +
                "Jorge Bucay", "What does it matter how many lovers you have if none of them gives you the universe?\n" +
                "Jacques Lacan", "What is necessary to change a person is to change his awareness of himself. \n" +
                "Abraham Maslow", "The stupid neither forgive nor forget; the naive forgive and forget; the wise forgive but do not forget. \n" +
                "Thomas Szasz")

        return quotes.random()
    }
}
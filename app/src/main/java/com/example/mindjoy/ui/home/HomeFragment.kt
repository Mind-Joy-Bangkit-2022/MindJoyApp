package com.example.mindjoy.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mindjoy.R
import com.example.mindjoy.databinding.FragmentHomeBinding
import com.example.mindjoy.ui.camera.CameraActivity
import com.example.mindjoy.ui.helper.UserDataPreferences
import com.example.mindjoy.ui.questions.QuestionActivity
import com.example.mindjoy.ui.viewmodel.SharedViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private val viewModel: SharedViewModel by activityViewModels()

    private lateinit var userPref: UserDataPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userPref = UserDataPreferences(requireContext().applicationContext)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.btnCheckMental.setOnClickListener {
            val intent = Intent(this@HomeFragment.requireContext(), QuestionActivity::class.java)
            startActivity(intent)
        }

        binding.btnCheckExpression.setOnClickListener {
            val intent = Intent(this.requireContext(), CameraActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateHome()
    }

    override fun onResume() {
        super.onResume()
        updateHome()
    }

    private fun updateHome(){
        val nameStatus = userPref.getName()
        val expressionStatus = userPref.getExpression()
        val mentalStatus = userPref.getMentalHealth()

        if (nameStatus != null) {
            binding.tvUsername.text = nameStatus
        } else {
            binding.tvUsername.text = "MindJoy User"
        }

        if (expressionStatus != null) {
            binding.cardTvExpresionStatus.text = expressionStatus
            when (expressionStatus) {
                "Happy" -> binding.imageViewExpression.setImageResource(R.drawable.smile)
                "Sad" -> binding.imageViewExpression.setImageResource(R.drawable.sad)
                "Neutral" -> binding.imageViewExpression.setImageResource(R.drawable.neutral)
            }
        } else {
            binding.cardTvExpresion.text = "-"
        }

        if (mentalStatus != null) {
            binding.cardTvMentalStatus.text = mentalStatus
        } else {
            binding.cardTvExpresionStatus.text = "-"
        }
    }
}
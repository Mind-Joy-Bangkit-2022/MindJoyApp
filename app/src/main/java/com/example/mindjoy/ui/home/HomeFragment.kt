package com.example.mindjoy.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mindjoy.databinding.FragmentHomeBinding
import com.example.mindjoy.ui.camera.CameraActivity
import com.example.mindjoy.ui.questions.QuestionActivity

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    companion object {
        const val EXTRA_USER_IDENTITY = "extra_user_identity"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.btnCheckMental.setOnClickListener {
            val intent = Intent (this@HomeFragment.requireContext(), QuestionActivity::class.java)
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

        if (arguments != null) {
            val name = arguments?.getString(EXTRA_USER_IDENTITY)
            binding.tvUsername.text = name
        }
    }
}
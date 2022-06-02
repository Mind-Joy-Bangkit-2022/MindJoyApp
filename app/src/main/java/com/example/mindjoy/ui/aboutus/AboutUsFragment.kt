package com.example.mindjoy.ui.aboutus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mindjoy.R
import com.example.mindjoy.databinding.FragmentAboutUsBinding
import com.example.mindjoy.ui.settings.SettingsFragment

class AboutUsFragment : Fragment() {
    private lateinit var binding: FragmentAboutUsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutUsBinding.inflate(layoutInflater)

        binding.tvBackButtonAboutUs.setOnClickListener {
            val fm = fragmentManager
            fm?.popBackStack()
        }

        return binding.root
    }
}
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

//    private val settingFragment = SettingsFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutUsBinding.inflate(layoutInflater)

//        binding.tvBackButtonAboutUs.setOnClickListener {
//            replaceFragment(settingFragment)
//        }

        return binding.root
    }

//    private fun replaceFragment(fragment: Fragment) {
//        if (fragment != null) {
//            val transaction = requireActivity().supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.fragment_container, fragment)
//            transaction.commit()
//        }
//    }
}
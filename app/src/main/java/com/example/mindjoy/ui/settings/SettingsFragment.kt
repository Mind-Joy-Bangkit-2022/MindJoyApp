package com.example.mindjoy.ui.settings

import SettingViewModelFactory
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ScrollCaptureSession
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mindjoy.R
import com.example.mindjoy.databinding.FragmentSettingsBinding
import com.example.mindjoy.ui.aboutus.AboutUsFragment
import com.example.mindjoy.ui.helper.Session
import com.example.mindjoy.ui.login.LoginActivity
import com.example.mindjoy.ui.splashscreen.dataStore

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding

    private val aboutUsFragment = AboutUsFragment()

    private lateinit var session: Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSettingsBinding.inflate(layoutInflater)

        val pref = SettingPreferences.getInstance(requireActivity().dataStore)

        val viewModelSetting =
            ViewModelProvider(this, SettingViewModelFactory(pref)).get(SettingViewModel::class.java)

        session = Session(requireContext().applicationContext)

        viewModelSetting.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.switchDarkMode.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.switchDarkMode.isChecked = false
            }
        }

        binding.switchDarkMode.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            viewModelSetting.saveThemeSetting(isChecked)
        }

        binding.tvAboutUs.setOnClickListener {
            replaceFragment(aboutUsFragment)
        }

        binding.tvLogOut.setOnClickListener {
            Intent(activity, LoginActivity::class.java).also {
                activity?.startActivity(it)
            }
            activity?.finish()
            session.saveLogin(false)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return binding.root
    }

    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack("replacement")
            transaction.commit()
        }
    }

}
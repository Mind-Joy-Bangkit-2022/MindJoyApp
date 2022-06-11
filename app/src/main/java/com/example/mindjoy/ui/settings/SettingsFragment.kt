package com.example.mindjoy.ui.settings

import SettingViewModelFactory
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.mindjoy.R
import com.example.mindjoy.databinding.FragmentSettingsBinding
import com.example.mindjoy.ui.aboutus.AboutUsFragment
import com.example.mindjoy.ui.helper.Session
import com.example.mindjoy.ui.login.LoginActivity
import com.example.mindjoy.ui.splashscreen.dataStore
import com.example.mindjoy.ui.viewmodel.SharedViewModel

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding

    private val aboutUsFragment = AboutUsFragment()

    private lateinit var session: Session

    private val viewModel: SharedViewModel by activityViewModels()

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
            alertDialogLogout()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

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

    private fun alertDialogLogout() {
        AlertDialog.Builder(requireActivity())
            .setTitle("Log Out")
            .setMessage("Are you sure want to log out?")
            .setPositiveButton("Yes") { _, _ ->
                session.saveLogin(false)
                Intent(activity, LoginActivity::class.java).also {
                    activity?.startActivity(it)
                }
                requireActivity().finish()
            }
            .setNegativeButton("No", null)
            .show()
    }
}
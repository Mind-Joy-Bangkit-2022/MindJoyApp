package com.example.mindjoy.ui

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mindjoy.R
import com.example.mindjoy.databinding.ActivityMainBinding
import com.example.mindjoy.databinding.FragmentSettingsBinding
import com.example.mindjoy.ui.aboutus.AboutUsFragment
import com.example.mindjoy.ui.camera.CameraActivity
import com.example.mindjoy.ui.home.HomeFragment
import com.example.mindjoy.ui.settings.SettingsFragment
import com.example.mindjoy.ui.viewmodel.SharedViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var settingBinding: FragmentSettingsBinding
    private val homeFragment = HomeFragment()
    private val settingsFragment = SettingsFragment()
    private val aboutUsFragment = AboutUsFragment()

    private val viewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        settingBinding = FragmentSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(homeFragment)
        viewModel.updateFragment(homeFragment)

        binding.bottomNavigation.menu.findItem(R.id.ic_home).isChecked = true

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home -> {
                    replaceFragment(homeFragment)
                    viewModel.updateFragment(homeFragment)
                }
                R.id.ic_camera -> {
                    Intent(this, CameraActivity::class.java).also {
                        startActivity(it)
                    }
                }
                R.id.ic_settings -> {
                    replaceFragment(settingsFragment)
                    viewModel.updateFragment(settingsFragment)
                }
            }
            true
        }

        settingBinding.tvAboutUs.setOnClickListener {
            replaceFragment(aboutUsFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.currentFragment.observe(this) {
            if (it != null) {
                replaceFragment(it)
                when (it) {
                    homeFragment -> binding.bottomNavigation.menu.findItem(R.id.ic_home).isChecked =
                        true
                    settingsFragment -> binding.bottomNavigation.menu.findItem(R.id.ic_settings).isChecked =
                        true
                }
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        viewModel.currentFragment.observe(this) {
            if (it != null) {
                replaceFragment(it)
                when (it) {
                    homeFragment -> binding.bottomNavigation.menu.findItem(R.id.ic_home).isChecked =
                        true
                    settingsFragment -> binding.bottomNavigation.menu.findItem(R.id.ic_settings).isChecked =
                        true
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}
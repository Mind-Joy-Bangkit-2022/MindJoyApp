package com.example.mindjoy.ui

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

import androidx.fragment.app.Fragment
import com.example.mindjoy.R
import com.example.mindjoy.databinding.ActivityMainBinding
import com.example.mindjoy.databinding.FragmentHomeBinding
import com.example.mindjoy.databinding.FragmentSettingsBinding
import com.example.mindjoy.ui.aboutus.AboutUsFragment
import com.example.mindjoy.ui.camera.CameraActivity
import com.example.mindjoy.ui.home.HomeFragment
import com.example.mindjoy.ui.questions.QuestionActivity
import com.example.mindjoy.ui.settings.SettingsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var settingBinding: FragmentSettingsBinding
    private val homeFragment = HomeFragment()
    private val settingsFragment = SettingsFragment()
    private val aboutUsFragment = AboutUsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        settingBinding = FragmentSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(homeFragment)

        binding.bottomNavigation.menu.findItem(R.id.ic_home).isChecked = true

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home -> replaceFragment(homeFragment)
                R.id.ic_camera -> Intent(this, CameraActivity::class.java).also {
                    startActivity(it)
                }
                R.id.ic_settings -> replaceFragment(settingsFragment)
            }
            true
        }

        settingBinding.tvAboutUs.setOnClickListener {
            replaceFragment(aboutUsFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.bottomNavigation.menu.findItem(R.id.ic_home).isChecked = true
        replaceFragment(homeFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}
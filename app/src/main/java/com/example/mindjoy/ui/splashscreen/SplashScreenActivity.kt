package com.example.mindjoy.ui.splashscreen

import SettingViewModelFactory
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.mindjoy.R
import com.example.mindjoy.ui.MainActivity
import com.example.mindjoy.ui.login.LoginActivity
import com.example.mindjoy.ui.settings.SettingPreferences
import com.example.mindjoy.ui.settings.SettingViewModel

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val delayTime: Long = 3000

        Handler(Looper.getMainLooper()).postDelayed({
            val intentToMainActivity = Intent(this, LoginActivity::class.java)
            startActivity(intentToMainActivity)
            finish()
        }, delayTime)

        val pref = SettingPreferences.getInstance(dataStore)
        val viewModelSetting =
            ViewModelProvider(this, SettingViewModelFactory(pref)).get(SettingViewModel::class.java)

        viewModelSetting.getThemeSettings().observe(this) {
                isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    private fun hideSystemUI() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }
}
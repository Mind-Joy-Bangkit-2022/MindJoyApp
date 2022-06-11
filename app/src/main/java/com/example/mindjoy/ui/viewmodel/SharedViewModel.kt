package com.example.mindjoy.ui.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private val _currentFragment = MutableLiveData<Fragment>()
    val currentFragment: LiveData<Fragment> = _currentFragment

    fun updateFragment(newFragment: Fragment) {
        _currentFragment.value = newFragment
    }
}
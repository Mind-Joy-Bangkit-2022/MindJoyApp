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

    private val _expressionStatus = MutableLiveData<String>()
    val expressionStatus: LiveData<String> = _expressionStatus

    fun updateExpression(newExpression: String) {
        _expressionStatus.value = newExpression
    }
}
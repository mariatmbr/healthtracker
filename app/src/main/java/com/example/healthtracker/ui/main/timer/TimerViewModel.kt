package com.example.healthtracker.ui.main.timer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimerViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Tasks Fragment"
    }
    val text: LiveData<String> = _text
}
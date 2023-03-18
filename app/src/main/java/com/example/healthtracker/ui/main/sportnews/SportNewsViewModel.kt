package com.example.healthtracker.ui.main.sportnews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SportNewsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is SportNews Fragment"
    }
    val text: LiveData<String> = _text
}
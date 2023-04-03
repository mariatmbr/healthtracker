package com.example.healthtracker.ui.main.savedIdeas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SavedIdeasViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Saved Ideas Fragment"
    }

    val text: LiveData<String> = _text
}
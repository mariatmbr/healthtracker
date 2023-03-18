package com.example.healthtracker.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healthtracker.model.UserData

class MainViewModel : ViewModel() {
    public val userData = MutableLiveData<UserData>()
}
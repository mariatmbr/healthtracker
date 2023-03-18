package com.example.healthtracker.ui.login

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.healthtracker.R
import com.example.healthtracker.databinding.FragmentLogoBinding

class LogoFragment : Fragment() {
    private lateinit var binding: FragmentLogoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogoBinding.inflate(inflater, container, false)

        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_logoFragment_to_slideFragment)
        }, 2000)

        return binding.root
    }
}
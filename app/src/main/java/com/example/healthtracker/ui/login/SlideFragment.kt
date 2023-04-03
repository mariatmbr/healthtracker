package com.example.healthtracker.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.healthtracker.R
import com.example.healthtracker.databinding.FragmentSlideBinding

class SlideFragment : Fragment() {
    private lateinit var binding: FragmentSlideBinding

    var pageNo = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSlideBinding.inflate(inflater, container, false)

        binding.yesButton.setOnClickListener {
            pageNo++;

            when (pageNo) {
                2 -> {
                    binding.imageView.setImageResource(R.drawable.slide2)
                    binding.slideText.text = "Ready to eat well and healthy?"
                }
                3 -> {
                    binding.imageView.setImageResource(R.drawable.slide3)
                    binding.slideText.text = "To avoid the recipes that you don't need?"
                }
                4 -> {
                    binding.imageView.setImageResource(R.drawable.slide4)
                    binding.slideText.text = "Welcome to Health Tracker!"
                }
                5 -> {
                    findNavController().navigate(R.id.action_slideFragment_to_loginFragment)
                }
            }
        }

        return binding.root;
    }
}
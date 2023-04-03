package com.example.healthtracker.ui.main.savedIdeas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.healthtracker.databinding.FragmentSavedIdeasBinding

class SavedIdeasFragment : Fragment() {

    private var _binding: FragmentSavedIdeasBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(SavedIdeasViewModel::class.java)

        _binding = FragmentSavedIdeasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSavedIdeas

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.healthtracker.ui.main.sportnews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.healthtracker.databinding.FragmentSportNewsBinding

class SportNewsFragment : Fragment() {

    private var _binding: FragmentSportNewsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val sportNewsViewModel =
            ViewModelProvider(this).get(SportNewsViewModel::class.java)

        _binding = FragmentSportNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSportNews
        sportNewsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
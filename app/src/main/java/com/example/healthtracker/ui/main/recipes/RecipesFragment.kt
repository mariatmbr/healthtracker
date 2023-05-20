package com.example.healthtracker.ui.main.recipes

import RecipesListAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthtracker.databinding.FragmentRecipesBinding

class RecipesFragment : Fragment() {
    val viewModel by viewModels<RecipesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val recipesListAdapter = RecipesListAdapter(emptyList());

        viewModel.recipes.observe(requireActivity()) {
            recipesListAdapter.setRecipes(it)
        }

        val binding = FragmentRecipesBinding.inflate(inflater, container, false)
        binding.recyclerView.adapter = recipesListAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(text: String?): Boolean {
                return true
            }

            override fun onQueryTextSubmit(text: String?): Boolean {
                viewModel.getRecipes(text ?: "")
                return true
            }
        })

        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getRecipes("");
    }
}


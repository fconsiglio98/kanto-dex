package com.example.pokedex

import android.annotation.SuppressLint
import com.example.pokedex.network.model.PokemonViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.adapters.PokemonAdapter
import com.example.pokedex.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private val sharedViewModel: PokemonViewModel by activityViewModels()
    private var binding: FragmentStartBinding? = null
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        return fragmentBinding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        sharedViewModel.pokemonData.observe(viewLifecycleOwner) {
            if (sharedViewModel.pokemonScreen.value == null) {
                sharedViewModel.setPokemonScreen(it[0])
            }
            pokemonAdapter.notifyDataSetChanged()
            binding?.apply {
                viewModel = sharedViewModel
                lifecycleOwner = viewLifecycleOwner
                startFragment = this@StartFragment
            }

        }
    }

    private fun setupRecyclerView() {
        pokemonAdapter =
            PokemonAdapter(sharedViewModel) { pokemon ->
                sharedViewModel.setPokemonScreen(pokemon)
            }
        binding?.recyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = pokemonAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}





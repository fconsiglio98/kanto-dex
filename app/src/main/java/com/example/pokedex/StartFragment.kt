package com.example.pokedex

import com.example.pokedex.network.model.PokemonViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer

class StartFragment : Fragment() {

    private val viewModel: PokemonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_start, container, false)

        viewModel.pokemonData.observe(viewLifecycleOwner, Observer { pokemonList ->
            // This code will be executed when new Pokemon data is fetched
            pokemonList.forEach { pokemon ->
                Log.d("PokemonData", "${pokemon.num} - ${pokemon.name} - ${pokemon.types} - ${pokemon.height} - ${pokemon.weight} - ${pokemon.img}")                // Do whatever you want with the fetched Pokemon data in your UI
            }
        })

        return view
    }
}

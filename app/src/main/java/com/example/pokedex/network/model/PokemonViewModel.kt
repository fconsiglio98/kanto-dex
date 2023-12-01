package com.example.pokedex.network.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.network.PokedexApi
import com.example.pokedex.network.Pokemon
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {

    private val _pokemonData = MutableLiveData<List<Pokemon>>()
    val pokemonData: LiveData<List<Pokemon>> get() = _pokemonData

    init {
        fetchPokemon()
    }

    fun fetchPokemon() {
        viewModelScope.launch {
            try {
                _pokemonData.value = PokedexApi.retrofitService.getPokemons().pokemons
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

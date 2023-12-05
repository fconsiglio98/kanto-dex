package com.example.pokedex.network.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.network.PokedexApi
import com.example.pokedex.network.Pokemon
import kotlinx.coroutines.launch

enum class PokedexApiStatus {LOADING, ERROR, DONE}

class PokemonViewModel : ViewModel() {

    private val _status = MutableLiveData<PokedexApiStatus>()
    val status: LiveData<PokedexApiStatus> = _status

    private val _pokemonData = MutableLiveData<List<Pokemon>>()
    val pokemonData: LiveData<List<Pokemon>> = _pokemonData

    private val _pokemonScreen =  MutableLiveData<Pokemon>()
    val pokemonScreen: MutableLiveData<Pokemon> = _pokemonScreen

    fun setPokemonScreen(pokemon: Pokemon){
        _pokemonScreen.value = pokemon
    }

    init {
        fetchPokemon()
    }

    private fun fetchPokemon() {
        viewModelScope.launch {
            _status.value = PokedexApiStatus.LOADING
            try {
                _pokemonData.value = PokedexApi.retrofitService.getPokemons().pokemons
                _status.value = PokedexApiStatus.DONE
            } catch (e: Exception) {
                _status.value = PokedexApiStatus.ERROR
                e.printStackTrace()
            }
        }
    }
}

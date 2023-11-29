package com.example.pokedex.network.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.network.Pokemon
import com.example.pokedex.network.PokemonApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL =
    "https://raw.githubusercontent.com/brunomoreirazup/kanto-pokedex-json/master/"

class PokemonViewModel : ViewModel() {

    private val _pokemonData = MutableLiveData<List<Pokemon>>()
    val pokemonData: LiveData<List<Pokemon>> get() = _pokemonData

    private val pokemonApiService: PokemonApiService

    init {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val client = OkHttpClient.Builder().build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        pokemonApiService = retrofit.create(PokemonApiService::class.java)

        // Fetch Pokemon data when the ViewModel is created
        fetchPokemon()
    }

    private fun fetchPokemon() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    pokemonApiService.getPokemons()
                }
                _pokemonData.value = response.pokemons
            } catch (e: Exception) {
                // Handle error, e.g., show error message
                e.printStackTrace()
            }
        }
    }
}

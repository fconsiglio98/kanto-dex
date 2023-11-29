package com.example.pokedex.network

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// Define Retrofit interface for API calls
interface PokemonApiService {
    @GET("pokedex.json")
    suspend fun getPokemons(): PokemonResponse
}

// Function to fetch and print Pokemon data
suspend fun fetchPokemonData() {
    val moshi = Moshi.Builder().build()
    val client = OkHttpClient.Builder().build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/brunomoreirazup/kanto-pokedex-json/master/")
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val service = retrofit.create(PokemonApiService::class.java)

    try {
        val response = service.getPokemons()
        val pokemonList = response.pokemons

        pokemonList.forEach { pokemon ->
            val parsedPokemon = Pokemon(
                pokemon.num,
                pokemon.img,
                pokemon.name,
                pokemon.types,
                pokemon.height,
                pokemon.weight
            )

            // Do whatever you want with the parsed Pokemon object
            println(parsedPokemon)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

suspend fun main() {
    fetchPokemonData()
}
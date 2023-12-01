package com.example.pokedex.network

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://raw.githubusercontent.com/fconsiglio98/kanto-pokedex/master/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val client = OkHttpClient.Builder().build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface PokedexApiService {
    @GET("kanto.json")
    suspend fun getPokemons(): PokemonResponse
}


object PokedexApi {
    val retrofitService : PokedexApiService by lazy {
        retrofit.create(PokedexApiService::class.java) }
}
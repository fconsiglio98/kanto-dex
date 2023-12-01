package com.example.pokedex.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pokemon(
    @Json(name = "num") val num: String,
    @Json(name = "img") val img: String,
    @Json(name = "name") val name: String,
    @Json(name = "types") val types: MutableList<String>,
    @Json(name = "height") val height: String,
    @Json(name = "weight") val weight: String,
    @Json(name = "description") val description: String
)
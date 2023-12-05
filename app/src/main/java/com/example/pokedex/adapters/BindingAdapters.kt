package com.example.pokedex.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColor
import androidx.core.graphics.toColorInt
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load
import com.example.pokedex.R
import com.example.pokedex.StartFragment
import com.example.pokedex.network.Pokemon
import com.example.pokedex.network.model.PokedexApiStatus
import okhttp3.internal.toHexString


@BindingAdapter("pokemonImage")
fun bindImage(imgView: ImageView, imgUrl: String?) {
        imgView.load(imgUrl) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.question_mark)
        }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("pokemonName")
fun bindName(textView: TextView, pokemon: Pokemon?) {
    if (pokemon != null) {
        textView.text = "${pokemon.num} ${pokemon.name.uppercase()}"
    } else {
        textView.text = "#??? ???"
    }

}

@BindingAdapter("pokemonType1")
fun bindType1(textView: TextView, data: MutableList<String>?) {
    if (data != null) {
        val type: String = if (data?.get(0)  == "Electric") {
            "Electr"
        } else if (data?.get(0) == "Fighting") {
            "Fight"
        } else {
            data?.get(0).toString()
        }

        textView.text = type.uppercase()

        val colorResId = when (textView.text.toString().lowercase()) {
            "normal" -> R.color.type_normal
            "fire" -> R.color.type_fire
            "water" -> R.color.type_water
            "electr" -> R.color.type_electric
            "grass" -> R.color.type_grass
            "ice" -> R.color.type_ice
            "fight" -> R.color.type_fighting
            "poison" -> R.color.type_poison
            "steel" -> R.color.type_steel
            "ground" -> R.color.type_ground
            "flying" -> R.color.type_flying
            "psychic" -> R.color.type_psychic
            "bug" -> R.color.type_bug
            "rock" -> R.color.type_rock
            "ghost" -> R.color.type_ghost
            "dragon" -> R.color.type_dragon
            else -> R.color.white
        }

        val colorValue = ContextCompat.getColor(textView.context, colorResId)

        val radius = 15 //radius will be 5dp

        val gradientDrawable = GradientDrawable()

        gradientDrawable.setColor(colorValue)
        gradientDrawable.cornerRadius = radius.toFloat()
        textView.background = gradientDrawable

        textView.setTextColor(Color.WHITE)

        if (textView.text.length > 5) {
            textView.letterSpacing = 0.2F
        } else if (textView.text.length > 6) {
            textView.letterSpacing = 0F
        }
    } else {
        textView.text = "???"

        val gradientDrawable = GradientDrawable()
        gradientDrawable.setColor(Color.WHITE)
        gradientDrawable.cornerRadius = 15F
        textView.background = gradientDrawable
        textView.setTextColor(Color.BLACK)
    }
}


@BindingAdapter("pokemonType2")
fun bindType2(textView: TextView, data: MutableList<String>?) {
    if (data != null) {
        if (data?.size == 2) {
            textView.visibility = View.VISIBLE
            textView.text = data[1]

            val type: String = if (data[1] == "Electric") {
                "Electr"
            } else if (data[1] == "Fighting") {
                "Fight"
            } else {
                data[1]
            }

            textView.text = type.uppercase()

            val colorResId = when (textView.text.toString().lowercase()) {
                "normal" -> R.color.type_normal
                "fire" -> R.color.type_fire
                "water" -> R.color.type_water
                "electr" -> R.color.type_electric
                "grass" -> R.color.type_grass
                "ice" -> R.color.type_ice
                "fight" -> R.color.type_fighting
                "poison" -> R.color.type_poison
                "steel" -> R.color.type_steel
                "ground" -> R.color.type_ground
                "flying" -> R.color.type_flying
                "psychic" -> R.color.type_psychic
                "bug" -> R.color.type_bug
                "rock" -> R.color.type_rock
                "ghost" -> R.color.type_ghost
                "dragon" -> R.color.type_dragon
                else -> R.color.white
            }

            val colorValue = ContextCompat.getColor(textView.context, colorResId)

            val radius = 15 //radius will be 5dp

            val gradientDrawable = GradientDrawable()

            gradientDrawable.setColor(colorValue)
            gradientDrawable.cornerRadius = radius.toFloat()
            textView.background = gradientDrawable

            textView.setTextColor(Color.WHITE)

            if (textView.text.length > 5) {
                textView.letterSpacing = 0.2F
            } else if (textView.text.length > 6) {
                textView.letterSpacing = 0F
            }
        } else {
            textView.text = ""
            textView.visibility = View.GONE
        }
    } else {
        textView.text = "???"

        val gradientDrawable = GradientDrawable()
        gradientDrawable.setColor(Color.WHITE)
        gradientDrawable.cornerRadius = 15F
        textView.background = gradientDrawable
        textView.setTextColor(Color.BLACK)
    }
}

@BindingAdapter("pokemonHeight")
fun bindHeight(textView: TextView, data: String?) {
    textView.text = data ?: "??? m"
}

@BindingAdapter("pokemonWeight")
fun bindWeight(textView: TextView, data: String?) {
    textView.text = data ?: "??? kg"
}

@BindingAdapter("pokemonDescription")
fun bindDescription(textView: TextView, data: String?) {
    textView.text = data ?: "No description available"
}

@BindingAdapter("pokemonList")
fun bindList(textView: TextView, data: String?) {
    if (data == null) {
        val imgValue = ContextCompat.getDrawable(textView.context, R.drawable.ic_connection_error)
        textView.background = imgValue
    }

}

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: PokedexApiStatus?) {
    when(status) {
        PokedexApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        PokedexApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        PokedexApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }

        else -> {}
    }
}




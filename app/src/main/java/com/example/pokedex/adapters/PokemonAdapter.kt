package com.example.pokedex.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.network.Pokemon
import com.example.pokedex.network.model.PokemonViewModel


class PokemonAdapter(
    private val viewModel: PokemonViewModel,
    private val onItemClick: (pokemon: Pokemon) -> Unit
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val pokemonButton: Button = view.findViewById(com.example.pokedex.R.id.button_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(com.example.pokedex.R.layout.pokemon_item_view, parent, false)
        return PokemonViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = viewModel.pokemonData.value?.get(position)
        val text = item?.name
        holder.pokemonButton.text = text

        holder.pokemonButton.setOnClickListener {
            // Retrieve the clicked item
            val clickedItem = viewModel.pokemonData.value?.get(holder.adapterPosition)
            clickedItem?.let { pokemon ->
                // Notify the fragment with the image URL of the clicked item
                onItemClick.invoke(pokemon)
            }
        }
    }

    override fun getItemCount(): Int {
        return viewModel.pokemonData.value?.size ?: 0
    }
}
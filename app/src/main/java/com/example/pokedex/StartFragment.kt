package com.example.pokedex

import android.content.res.ColorStateList
import com.example.pokedex.network.model.PokemonViewModel
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.pokedex.adapters.PokemonAdapter
import com.example.pokedex.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private val viewModel: PokemonViewModel by activityViewModels()
    private var binding: FragmentStartBinding? = null
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.pokemonData.observe(viewLifecycleOwner) { pokemonList ->
            pokemonAdapter.notifyDataSetChanged()
            binding?.imageView?.load(pokemonList[0].img) {
                // You can apply transformations, error handling, etc., if needed
                placeholder(R.drawable.poke_ball_icon) // Placeholder image while loading
                error(R.drawable.ic_launcher_foreground) // Error image if loading fails
                // Optionally, you can specify other parameters like transformations, etc.
            }
        }
    }

    private fun setupRecyclerView() {
        pokemonAdapter = PokemonAdapter(viewModel) { imageUrl, num, name, types, height, weight, description ->
            // Load the image URL in the imageView
            binding?.imageView?.load(imageUrl) {
                placeholder(R.drawable.poke_ball_icon)
                error(R.drawable.ic_launcher_foreground)
            }

            binding?.nameTextView?.text = "$num ${name.uppercase()}"

            typeSetup(types, binding!!.typeTextView, binding!!.typeTextView2)

            binding!!.heightTextView.text = "HT    $height"
            binding!!.weightTextView.text = "WT    $weight"

            binding!!.descriptionTextView.text = description

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

    private fun reduceLetterSpacing(textView: TextView) {
        if (textView.text.length > 5) {
            textView.letterSpacing = 0.2F
        } else if (textView.text.length > 6) {
            textView.letterSpacing = 0F
        }
    }


    private fun changeTypeBackgroundColor(textView: TextView) {
        val colorResId = when (textView.text.toString().lowercase()) {
            "normal" -> R.color.type_normal
            "fire" -> R.color.type_fire
            "water" -> R.color.type_water
            "electr" -> R.color.type_electric
            "grass" -> R.color.type_grass
            "ice" -> R.color.type_ice
            "fight" -> R.color.type_fighting
            "poison" -> R.color.type_poison
            "ground" -> R.color.type_ground
            "flying" -> R.color.type_flying
            "psychic" -> R.color.type_psychic
            "bug" -> R.color.type_bug
            "rock" -> R.color.type_rock
            "ghost" -> R.color.type_ghost
            "dragon" -> R.color.type_dragon
            else -> R.color.white
        }

        val color = ContextCompat.getColor(requireContext(), colorResId)
        val colorStateList = ContextCompat.getColorStateList(requireContext(), colorResId)
        textView.backgroundTintList = colorStateList ?: ColorStateList.valueOf(color)
    }


    private fun typeSetup(
        types: MutableList<String>,
        typeTextView1: TextView,
        typeTextView2: TextView
    ) {
        for (i in types.indices) {
            if (types[i] == "Electric") {
                types[i] = "Electr"
            } else if (types[i] == "Fighting") {
                types[i] = "Fight"
            }
            if (types.size > 1) {
                binding?.typeTextView?.text = types[0].uppercase()
                binding?.let {
                    reduceLetterSpacing(it.typeTextView)
                    changeTypeBackgroundColor(it.typeTextView)
                }
                binding?.typeTextView2?.text = types[1].uppercase()
                binding?.let {
                    reduceLetterSpacing(it.typeTextView2)
                    changeTypeBackgroundColor(it.typeTextView2)
                }
                binding?.typeTextView2?.visibility = View.VISIBLE
            } else {
                binding?.typeTextView?.text = types[0].uppercase()
                binding?.let {
                    reduceLetterSpacing(it.typeTextView)
                    changeTypeBackgroundColor(it.typeTextView)
                }
                binding?.typeTextView2?.visibility = View.GONE
            }
        }
    }
}





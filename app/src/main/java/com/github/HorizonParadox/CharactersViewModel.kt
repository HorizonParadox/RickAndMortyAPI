package com.github.HorizonParadox

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.HorizonParadox.databinding.FragmentMainBinding
import com.github.HorizonParadox.databinding.ItemSingleCharacterBinding


class ListAdapter(private val list: List<CharacterItem>) :
  RecyclerView.Adapter<ListAdapter.CharactersViewHolder>() {


  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
    val binding =
      ItemSingleCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return CharactersViewHolder(binding)
  }

  override fun getItemCount(): Int = list.size

  override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
    with(holder) {
      with(binding) {
        with(list[position]) {
          Glide.with(holder.itemView.context).load(R.drawable.test_image).into(characterImageView)
          characterNameTextView.text = name
          characterSpeciesTextView.text = species
          characterGenderTextView.text = gender
        }
      }
    }
  }


  inner class CharactersViewHolder(val binding: ItemSingleCharacterBinding) :
    RecyclerView.ViewHolder(binding.root)
}


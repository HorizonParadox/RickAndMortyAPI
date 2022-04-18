package com.github.HorizonParadox.view

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.HorizonParadox.network.CharacterResponse
import com.github.HorizonParadox.databinding.ItemSingleCharacterBinding

class CharactersViewModel(val binding: ItemSingleCharacterBinding) :
  RecyclerView.ViewHolder(binding.root) {

  fun bind(binding: ItemSingleCharacterBinding, element: CharacterResponse) {
    with(binding) {
      with(element) {
        Glide.with(characterImageView).load(imageUrl).into(characterImageView)
        characterNameTextView.text = characterName
        characterSpeciesTextView.text = species
        characterGenderTextView.text = gender
      }
    }
  }



}
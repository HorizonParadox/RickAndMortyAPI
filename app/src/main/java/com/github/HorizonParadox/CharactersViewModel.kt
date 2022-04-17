package com.github.HorizonParadox

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.HorizonParadox.databinding.ItemSingleCharacterBinding

class CharactersViewModel(val binding: ItemSingleCharacterBinding) :
  RecyclerView.ViewHolder(binding.root) {


  fun bind(binding: ItemSingleCharacterBinding, element: CharacterItem?) {
    with(binding) {
      with(element) {
        Glide.with(characterImageView).load(this?.image).into(characterImageView)
        characterNameTextView.text = this!!.name
        characterSpeciesTextView.text = this.species
        characterGenderTextView.text = this.gender
      }
    }
  }





}
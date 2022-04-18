package com.github.HorizonParadox.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.HorizonParadox.network.CharacterResponse
import com.github.HorizonParadox.databinding.ItemSingleCharacterBinding


class CharacterAdapter(private val list: List<CharacterResponse>) :
  RecyclerView.Adapter<CharactersViewModel>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewModel {
    val binding =
      ItemSingleCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return CharactersViewModel(binding)
  }

  override fun getItemCount(): Int = list.size

  override fun onBindViewHolder(model: CharactersViewModel, position: Int) {
    with(model) {
      bind(model.binding, list[position])
    }
  }

}




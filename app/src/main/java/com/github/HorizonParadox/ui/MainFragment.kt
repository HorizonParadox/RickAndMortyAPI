package com.github.HorizonParadox.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.HorizonParadox.CharacterItem
import com.github.HorizonParadox.ListAdapter
import com.github.HorizonParadox.databinding.FragmentMainBinding


class MainFragment : Fragment() {

  private lateinit var binding: FragmentMainBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentMainBinding.inflate(layoutInflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.recyclerView.apply {
      layoutManager = LinearLayoutManager(activity)
      adapter = ListAdapter(
        listOf(
          CharacterItem(0, "Anie", "Human", "Female"),
          CharacterItem(1, "Ben", "Human", "Male"),
          CharacterItem(1, "Ben", "Human", "Male"),
          CharacterItem(1, "Ben", "Human", "Male"),
          CharacterItem(1, "Ben", "Human", "Male"),
          CharacterItem(1, "Ben", "Human", "Male")
        )
      )
    }
  }
}
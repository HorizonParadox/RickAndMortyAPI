package com.github.HorizonParadox.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.HorizonParadox.databinding.FragmentDetaliedBinding



class DetailedCharacterFragment: Fragment() {

  private lateinit var binding: FragmentDetaliedBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentDetaliedBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

  }
}
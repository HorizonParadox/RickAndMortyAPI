package com.github.HorizonParadox.ui

import android.os.Bundle
import android.util.Log
import android.util.Property.of
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.Insets.of
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.HorizonParadox.APITest.QuestAPI
import com.github.HorizonParadox.APITest.QuestListModel
import com.github.HorizonParadox.App
import com.github.HorizonParadox.CharacterItem
import com.github.HorizonParadox.CharacterAdapter
import com.github.HorizonParadox.databinding.FragmentMainBinding
import com.github.HorizonParadox.network.base.NetworkComponent




import retrofit2.Call
import retrofit2.Callback
import retrofit2.Invocation.of
import retrofit2.Response


class MainFragment : Fragment() {

  private lateinit var binding: FragmentMainBinding
  private val api = NetworkComponent.createApi()

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

    binding.testAPITextView.text = CharacterAdapter(listOf(getItem())).toString()
    binding.recyclerView.apply {
      layoutManager = LinearLayoutManager(activity)
      //adapter = CharacterAdapter(listOf(getItem()))

    }
  }


  private fun getItem(): CharacterItem {
    val response = api.character()
    val characters = CharacterItem(response.id, response.imageUrl, response.name, response.species, response.gender)
    return characters
  }

}


package com.github.HorizonParadox.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.HorizonParadox.network.QuestAPI
import com.github.HorizonParadox.network.CharacterListResponse
import com.github.HorizonParadox.view.CharacterAdapter
import com.github.HorizonParadox.databinding.FragmentMainBinding
import retrofit2.*


import retrofit2.converter.gson.GsonConverterFactory


class MainFragment : Fragment() {

  private lateinit var binding: FragmentMainBinding


  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentMainBinding.inflate(layoutInflater, container, false)
    binding.progressBar.visibility = View.INVISIBLE
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    binding.recyclerView.layoutManager = LinearLayoutManager(activity)
    binding.progressBar.visibility = View.VISIBLE
    getData(1)

  }

  private fun getData(page: Int) {

    val retrofitBuilder = Retrofit.Builder()
      .baseUrl("https://rickandmortyapi.com/")
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(QuestAPI::class.java)
    val retrofitData = retrofitBuilder.getCharacter(1)

    retrofitData.enqueue(object : Callback<CharacterListResponse> {
      override fun onResponse(
        call: Call<CharacterListResponse>,
        response: Response<CharacterListResponse>
      ) {
        Log.e("TAG", response.body().toString())
        val responseBody = response.body()?.results
        //binding.testAPITextView.text = responseBody.toString()
        binding.recyclerView.adapter = CharacterAdapter(responseBody!!)
        binding.progressBar.visibility = View.INVISIBLE
      }

      override fun onFailure(call: Call<CharacterListResponse>, t: Throwable) {
        TODO("Not yet implemented")
      }

    })
  }


}




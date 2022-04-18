package com.github.HorizonParadox.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.HorizonParadox.network.QuestAPI
import com.github.HorizonParadox.network.CharactersListResponse
import com.github.HorizonParadox.view.CharacterAdapter
import com.github.HorizonParadox.databinding.FragmentMainBinding
import retrofit2.*


import retrofit2.converter.gson.GsonConverterFactory
import androidx.recyclerview.widget.RecyclerView
import com.github.HorizonParadox.network.CharacterResponse
import org.jetbrains.annotations.NotNull


class MainFragment : Fragment() {

  private lateinit var binding: FragmentMainBinding
  val myData = mutableListOf<CharacterResponse>()
  var page = 0
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentMainBinding.inflate(layoutInflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    with(binding) {

      recyclerView.layoutManager = LinearLayoutManager(activity)
      setAdapterData(binding)

      recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
          super.onScrollStateChanged(recyclerView, newState)
          if (!recyclerView.canScrollVertically(1)) {
            if(page!=42){
              page++
              val newData = getData(page)
              updateData(newData)
            }
          }
        }
      })
    }
  }

  private fun updateData(newData: Call<CharactersListResponse>){
    newData.enqueue(object : Callback<CharactersListResponse>{
      override fun onResponse(
        call: Call<CharactersListResponse>,
        response: Response<CharactersListResponse>
      ) {
        val responseResults= response.body()?.results
        if (responseResults != null) {
          myData.addAll(responseResults)
          binding.progressBar.visibility = View.VISIBLE
          binding.recyclerView.adapter?.notifyDataSetChanged()
          binding.progressBar.visibility = View.INVISIBLE
        }
      }

      override fun onFailure(call: Call<CharactersListResponse>, t: Throwable) {
        TODO("Not yet implemented")
      }

    })
  }

  private fun setAdapterData(myBinding: FragmentMainBinding) {

    page = 1
    val retrofitData = getData(page)
    retrofitData.enqueue(object : Callback<CharactersListResponse> {
      override fun onResponse(
        call: Call<CharactersListResponse>,
        response: Response<CharactersListResponse>
      ) {
        Log.e("TAG", response.body().toString())
        val responseBody = response.body()?.results
        //binding.testAPITextView.text = responseBody.toString()
        if (responseBody != null) {
          myData.addAll(responseBody)
        }
        myBinding.progressBar.visibility = View.VISIBLE
        myBinding.recyclerView.adapter = CharacterAdapter(myData)
        myBinding.progressBar.visibility = View.INVISIBLE
      }

      override fun onFailure(call: Call<CharactersListResponse>, t: Throwable) {
        TODO("Not yet implemented")
      }
    })
  }

  private fun getData(page: Int): Call<CharactersListResponse> {
    val retrofitBuilder = Retrofit.Builder()
      .baseUrl("https://rickandmortyapi.com/")
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(QuestAPI::class.java)
    return retrofitBuilder.getCharacter(page)
  }
}




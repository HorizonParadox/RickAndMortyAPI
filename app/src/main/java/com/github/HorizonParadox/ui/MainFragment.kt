package com.github.HorizonParadox.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isEmpty
import androidx.core.view.isNotEmpty
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
import android.R


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
      if (recyclerView.isEmpty()) {
        setAdapterData(binding)
      }

      recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
          super.onScrollStateChanged(recyclerView, newState)

          if (!recyclerView.canScrollVertically(1)) {
            if (page != 42) {
              page++
              updateData(page)
            }
          }
        }
      })
    }
  }

  private fun updateData(myPage: Int) {
    binding.progressBar.visibility = View.VISIBLE
    val newData = getData(myPage)
    newData.enqueue(object : Callback<CharactersListResponse> {
      override fun onResponse(
        call: Call<CharactersListResponse>,
        response: Response<CharactersListResponse>
      ) {
        val responseResults = response.body()?.results
        if (responseResults != null) {
          myData.addAll(responseResults)
          binding.recyclerView.adapter?.notifyDataSetChanged()
        }
        binding.progressBar.visibility = View.INVISIBLE
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
        myBinding.recyclerView.adapter =
          CharacterAdapter(myData, object : CharacterAdapter.OnItemClickListener {
            override fun onItemClick(info: CharacterResponse) {
              val messageToAnotherFragment = info.id
              Log.e("TAG", messageToAnotherFragment.toString())
              val fragment = DetailedCharacterFragment()
              val bundle = Bundle()
              bundle.putInt("position", messageToAnotherFragment)
              fragment.arguments = bundle

              activity!!.supportFragmentManager.beginTransaction()
                .add(binding.mainFragment.id, fragment, "findThisFragment")
                .addToBackStack(null)
                .commit()

            }
          })
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
    return retrofitBuilder.getCharacterList(page)
  }
}




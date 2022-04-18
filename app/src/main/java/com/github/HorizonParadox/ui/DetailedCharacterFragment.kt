package com.github.HorizonParadox.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.github.HorizonParadox.R
import com.github.HorizonParadox.databinding.FragmentDetaliedBinding
import com.github.HorizonParadox.network.CharacterResponse
import com.github.HorizonParadox.network.CharactersListResponse
import com.github.HorizonParadox.network.QuestAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DetailedCharacterFragment : Fragment() {

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
    val bundle = this.arguments
    val myInt = bundle!!.getInt("position", 0)
    getData(binding,myInt)

  }

  private fun getData(binding: FragmentDetaliedBinding, character: Int) {
    val retrofitBuilder = Retrofit.Builder()
      .baseUrl("https://rickandmortyapi.com/")
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(QuestAPI::class.java)

    val retrofitData = retrofitBuilder.getCharacterInfo(character)
    retrofitData.enqueue(object : Callback<CharacterResponse> {
      override fun onResponse(
        call: Call<CharacterResponse>,
        response: Response<CharacterResponse>
      ) {
        val result = response.body()
        if (result != null) {
          with(binding){
            with(result){
              Glide.with(characterImageView).load(imageUrl).into(characterImageView)
              characterNameTextView.text = characterName
              characterStatusTextView.text = status
              Log.e("Status", status)
              when(status){
                "Dead" -> statusImageView.setImageResource(R.drawable.status_logo_dead)
                "Alive" -> statusImageView.setImageResource(R.drawable.status_logo_alive)
                "unknown" -> statusImageView.setImageResource(R.drawable.status_logo_unknown)
              }
              characterSpeciesTextView.text = species
              characterGenderTextView.text = gender
              characterEpisodeTextView.text = episode.size.toString()
              characterLocationTextView.text = location.name
            }
          }
        }
      }

      override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
      }

    })
  }

}

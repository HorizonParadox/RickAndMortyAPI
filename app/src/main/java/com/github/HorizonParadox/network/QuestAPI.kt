package com.github.HorizonParadox.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface QuestAPI {
  @GET("/api/character/")
  fun getCharacterList(@Query("page") page: Int): Call<CharactersListResponse>

  @GET("/api/character/{id}")
  fun getCharacterInfo(@Path("id") id: Int): Call<CharacterResponse>
}
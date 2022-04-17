package com.github.HorizonParadox.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface QuestAPI {
  @GET("/api/character/")
  fun getCharacter(@Query("page") page: Int): Call<CharacterListResponse>

  @GET("./api/character")
  fun getCharacterList(): Call<CharacterListResponse>

}
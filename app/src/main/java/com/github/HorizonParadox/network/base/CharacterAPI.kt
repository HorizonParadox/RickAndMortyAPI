package com.github.HorizonParadox.network.base

import com.github.HorizonParadox.network.base.PagesResponse
import retrofit2.http.GET

interface CharacterAPI {
  @GET("./api/character/19")
   fun character(): CharactersDTO
}
package com.github.HorizonParadox.network

import com.google.gson.annotations.SerializedName

data class CharacterListResponse(
  @SerializedName("info") val info: CharacterInfoResponse,
  @SerializedName("results") val results: List<CharacterResponse>
)

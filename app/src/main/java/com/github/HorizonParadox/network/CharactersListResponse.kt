package com.github.HorizonParadox.network

import com.google.gson.annotations.SerializedName

data class CharactersListResponse(
  @SerializedName("info") val info: CharacterPaginationInfoResponse,
  @SerializedName("results") val results: List<CharacterResponse>
)

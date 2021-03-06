package com.github.HorizonParadox.network

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
  @SerializedName("id") val id: Int,
  @SerializedName("image") val imageUrl: String,
  @SerializedName("name") val characterName: String,
  @SerializedName("species") val species: String,
  @SerializedName("gender") val gender: String,
  @SerializedName("status") val status: String,
  @SerializedName("location") val location: LocationResponse,
  @SerializedName("episode") val episode: List<String>
)

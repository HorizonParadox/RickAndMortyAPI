package com.github.HorizonParadox.network.base

import com.google.gson.annotations.SerializedName

data class CharactersDTO (
  @SerializedName("id") val id: Int,
  @SerializedName("image") val imageUrl: String,
  @SerializedName("name") val name: String,
  @SerializedName("species") val species: String,
  @SerializedName("gender") val gender: String
  )

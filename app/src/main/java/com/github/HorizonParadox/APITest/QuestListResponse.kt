package com.github.HorizonParadox.APITest

import com.google.gson.annotations.SerializedName

data class QuestListResponse(
  @SerializedName("id") val id: Int,
  @SerializedName("image") val imageUrl: String,
  @SerializedName("name") val characterName: String,
  @SerializedName("species") val species: String,
  @SerializedName("gender") val gender: String
)

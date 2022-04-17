package com.github.HorizonParadox.network.base

import com.google.gson.annotations.SerializedName

data class PagesResponse(
  @SerializedName("count") val count : Int,
  @SerializedName("pages") val pages: Int,
  @SerializedName("next") val nextUrl: String,
  @SerializedName("prev") val prevUrl: String,
  @SerializedName("result") val results: List<CharactersDTO>
)

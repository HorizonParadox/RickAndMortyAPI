package com.github.HorizonParadox.network

import com.google.gson.annotations.SerializedName

data class CharacterPaginationInfoResponse(
  @SerializedName("count") val count: Int,
  @SerializedName("pages") val pages: Int,
  @SerializedName("next") val nextPage: String?,
  @SerializedName("prev") val prevPage: String?
)
package com.github.HorizonParadox.APITest

import io.reactivex.Single
import retrofit2.http.GET

interface QuestAPI {
  @GET("./api/character/2")
  fun getQuestList(): Single<QuestListResponse>
}
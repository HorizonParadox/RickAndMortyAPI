package com.github.HorizonParadox.network.base

import com.github.HorizonParadox.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface NetworkComponent {

  companion object {
    private const val BASE_URL = "https://rickandmortyapi.com/"
    fun createApi(): CharacterAPI =
      Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(
          OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
              level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            })
            .build()
        ).build()
        .create(CharacterAPI::class.java)
  }
}
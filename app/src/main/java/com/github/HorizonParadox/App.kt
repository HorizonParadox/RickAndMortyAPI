package com.github.HorizonParadox

import android.app.Application
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {
  //lateinit var questAPI: QuestAPI
  override fun onCreate() {
    super.onCreate()
    //configureRetrofit()
  }

  private fun configureRetrofit() {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    val okHttpClient = OkHttpClient.Builder()
      .addInterceptor(httpLoggingInterceptor)
      .build()

    val retrofit = Retrofit.Builder()
      .baseUrl("https://rickandmortyapi.com/")
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build()
    //questAPI = retrofit.create(QuestAPI::class.java)
  }
}
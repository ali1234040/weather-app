package com.example.waetherapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val mainApi: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

}
interface ApiService {

    @GET("v1/current.json")
   suspend fun getCurrentWeather(
       @Query("key") apiKey: String = "71612333790846d4aa351602251509" ,
       @Query("q") city: String = "tehran",
       @Query("lang") lang: String = "fa"
   ): CurrentWeatherModel


  @GET("v1/history.json")
   suspend fun getPastWeather(
       @Query("key") apiKey: String = "71612333790846d4aa351602251509" ,
       @Query("q") city: String = "tehran",
       @Query("lang") lang: String = "fa",
       @Query("dt") date: String
   ): PastWeatherModel


}

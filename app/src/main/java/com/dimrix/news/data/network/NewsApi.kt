package com.dimrix.news.data.network

import com.dimrix.news.data.network.enteties.NewsApiResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://newsapi.org/"
private const val KEY_API = "f127a00e0b494664b88164c6bca08897"


private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

object NewsApiClient {
    val retrofitService: NewsApiService by lazy { retrofit.create(NewsApiService::class.java) }
}


interface NewsApiService {

    @GET("v2/everything?q=bitcoin&sortBy=publishedAt&apiKey=$KEY_API")
    suspend fun getNewsAsync(): NewsApiResponse
}
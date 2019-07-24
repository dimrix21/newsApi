package com.dimrix.news.data

import androidx.lifecycle.MutableLiveData
import com.dimrix.news.data.network.NewsApiService
import com.dimrix.news.data.network.enteties.NewsApiResponse

class NewsRepository(private val apiService: NewsApiService) {


    val newsItems = MutableLiveData<List<NewsApiResponse.Article>>()

    suspend fun fetchNews() {
        newsItems.value = apiService.getNewsAsync().articles
    }

}
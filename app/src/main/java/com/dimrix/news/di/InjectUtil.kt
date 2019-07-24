package com.dimrix.news.di

import com.dimrix.news.data.NewsRepository
import com.dimrix.news.data.network.NewsApiClient
import com.dimrix.news.data.network.NewsApiService

class InjectUtil {

    companion object {

        private fun provideApiService(): NewsApiService = NewsApiClient.retrofitService

        fun provideRepository(): NewsRepository = NewsRepository(provideApiService())
    }


}
package com.dimrix.news.di

import android.content.Context
import com.dimrix.news.data.NewsRepository
import com.dimrix.news.data.local.db.AppDatabase
import com.dimrix.news.data.network.NewsApiClient
import com.dimrix.news.data.network.NewsApiService

class InjectUtil {

    companion object {

        private fun provideApiService(): NewsApiService = NewsApiClient.retrofitService

        fun provideRepository(context: Context): NewsRepository =
            NewsRepository(provideArticleDao(context), provideApiService())

        private fun provideArticleDao(context: Context) =
            provideAppDB(context)!!.articleItemDao()

        private fun provideAppDB(context: Context) =
            AppDatabase.getInstance(context)

    }


}


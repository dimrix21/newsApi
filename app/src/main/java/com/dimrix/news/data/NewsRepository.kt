package com.dimrix.news.data

import androidx.lifecycle.LiveData
import com.dimrix.news.data.local.db.item.ArticleItemDao
import com.dimrix.news.data.local.db.item.ArticleItemEntry
import com.dimrix.news.data.network.NewsApiService

class NewsRepository(
    private val articleItemDao: ArticleItemDao,
    private val apiService: NewsApiService
) {


    suspend fun fetchNews() {
        val articles = apiService.getNewsAsync().articles

        val map = articles.map { article ->

            // TODO : url ro image sometimes null, this is a PATCh fix
            // need to check why and how
            val urlToImage: String = when (article.urlToImage.isNullOrEmpty()) {
                true -> ""
                false -> article.urlToImage
            }

            ArticleItemEntry(
                0,
                article.description,
                article.publishedAt,
                article.title,
                article.url,
                urlToImage
            )

        }
        articleItemDao.replaceAllData(*map.toTypedArray())
    }


    val feed: LiveData<List<ArticleItemEntry>> by lazy(0) {
        articleItemDao.getAll()
    }

}
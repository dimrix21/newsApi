package com.dimrix.news.screens.webview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Factory method that allows us to create a ViewModel with a constructor that takes a
 * [WebViewViewModelFactory] and an Link for the current [ArticleItemEntry]
 */
class WebViewViewModelFactory(private val url: String) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        @Suppress("UNCHECKED_CAST")
        return WebViewViewModel(url) as T
    }
}
package com.dimrix.news.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dimrix.news.data.NewsRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(repository: NewsRepository) : ViewModel() {

    val myItems = repository.newsItems

    init {
        viewModelScope.launch {
            repository.fetchNews()
        }
    }
}



package com.dimrix.news.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dimrix.news.data.NewsRepository


@Suppress("UNCHECKED_CAST")
class MainActivityViewModelFactory(private val repository: NewsRepository) :
    ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(repository) as T
    }
}
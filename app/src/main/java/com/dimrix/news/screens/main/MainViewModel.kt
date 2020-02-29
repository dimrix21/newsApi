package com.dimrix.news.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dimrix.news.data.NewsRepository
import com.dimrix.news.data.local.db.item.ArticleItemEntry
import kotlinx.coroutines.launch

class MainViewModel(private val repository: NewsRepository) : ViewModel() {

    private val myItems = repository.feed
    private val _urlSelected = MutableLiveData<String>()
    private val urlSelected: LiveData<String>
        get() = _urlSelected

    fun getObservableNewsItems(): LiveData<List<ArticleItemEntry>> {
        return myItems
    }

    fun onCellClicked(url: String) {
        _urlSelected.value = url
        _urlSelected.value = null
    }

    fun getObservableUrl(): LiveData<String> {
        return urlSelected
    }

    fun onResume() {
        viewModelScope.launch {
            repository.fetchNews()
        }
    }

}



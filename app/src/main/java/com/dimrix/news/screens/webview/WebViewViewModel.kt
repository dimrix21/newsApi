package com.dimrix.news.screens.webview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WebViewViewModel(url: String) : ViewModel() {

    private val _url: MutableLiveData<String> = MutableLiveData()

    val url: LiveData<String>
        get() = _url
 
    init {
        _url.value = url
    }

}
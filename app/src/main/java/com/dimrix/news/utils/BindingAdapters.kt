/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.dimrix.news.utils

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dimrix.news.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*


/**
 * Uses the Glide library to load an image by URL into an [ImageView]
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(
                        R.drawable.loading_animation
                    )
                    .error(R.drawable.ic_broken_image)
            ).into(imgView)
    }
}

/**
 *  Loading webView with provided url
 */
@SuppressLint("SetJavaScriptEnabled")
@BindingAdapter("loadUrl")
fun bindUrl(webView: WebView, url: String) {
    url.let {

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webView.loadUrl(url)
    }
}


@BindingAdapter("attachProgressBarAndContainer")
fun bindProgressBar(webView: WebView, progressBar: ProgressBar) {
    // Set web view chrome client
    webView.webChromeClient = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            progressBar.progress = newProgress
        }
    }
}


@BindingAdapter("attachLoadingContainer")
fun bindView(webView: WebView, container: View) {
    webView.webViewClient = object : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            container.visibility = View.VISIBLE
            super.onPageStarted(view, url, favicon)
        }

        override fun onPageCommitVisible(view: WebView?, url: String?) {
            container.visibility = View.GONE
            super.onPageCommitVisible(view, url)
        }


    }
}

@BindingAdapter("attachFloatingButton")
fun bindRecyclerViewWithFB(recyclerView: RecyclerView, fb: FloatingActionButton) {
    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            if (dy > 0 && fb.isShown) {
                fb.hide()
            } else if (dy < 0 && !fb.isShown) {
                fb.show()
            }
        }
    })
}

@BindingAdapter("getStringFromDate")
fun bindDate(textView: TextView, time: Date) {
    // val instance = Calendar.getInstance()
    // instance.timeInMillis = time
    val localDateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ENGLISH)

    textView.text = localDateFormat.format(time.time)
}




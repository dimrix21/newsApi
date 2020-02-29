package com.dimrix.news.screens.webview

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dimrix.news.databinding.FragmentWebviewBinding

class WebViewFragment : Fragment() {

    private lateinit var webView: WebView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val url = WebViewFragmentArgs.fromBundle(arguments!!).url
        //  Get the ViewModel from the factory
        val factory = WebViewViewModelFactory(url)

        val viewModel = ViewModelProvider(this, factory).get(WebViewViewModel::class.java)

        val binding = FragmentWebviewBinding.inflate(LayoutInflater.from(context))
        binding.viewModel = viewModel
        binding.executePendingBindings()

        webView = binding.fragWebviewWebview

        return binding.root

    }


    // handle back presses when inner url loaded just go back and not pop this fragment
    override fun onResume() {
        super.onResume()
        webView.isFocusableInTouchMode = true
        webView.requestFocus()
        webView.setOnKeyListener { _, keyCode, event ->
            if (!webView.canGoBack()) {
                false
            } else if (event.action == KeyEvent.ACTION_DOWN) {
                true
            } else if (event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                webView.goBack()
                true
            } else {
                false
            }
        }

    }

}
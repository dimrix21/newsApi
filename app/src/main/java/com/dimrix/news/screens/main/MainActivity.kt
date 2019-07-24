package com.dimrix.news.screens.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimrix.news.databinding.ActivityMainBinding
import com.dimrix.news.di.InjectUtil
import com.dimrix.news.screens.main.list.ArticlesAdapter


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var articlesAdapter: ArticlesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, com.dimrix.news.R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this, MainActivityViewModelFactory(InjectUtil.provideRepository()))
            .get(MainActivityViewModel::class.java)

        initializeRecyclerView()
        viewModel.myItems.observe(this, Observer { list ->
            articlesAdapter.submitList(list)
        })


    }

    private fun initializeRecyclerView() {

        articlesAdapter = ArticlesAdapter(::cellClickCallback)

        binding.mainRv.apply {
            val layoutManager = LinearLayoutManager(context)
            this.layoutManager = layoutManager
            this.adapter = articlesAdapter
        }
    }

    private fun cellClickCallback(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }
}
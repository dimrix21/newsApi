package com.dimrix.news.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimrix.news.R
import com.dimrix.news.databinding.MainFragmentBinding
import com.dimrix.news.di.InjectUtil
import com.dimrix.news.screens.main.list.ArticlesAdapter

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private lateinit var articlesAdapter: ArticlesAdapter

    /**ß
     * Lazily initialize our [MainViewModel].
     */
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(InjectUtil.provideRepository(context!!))).get(
            MainViewModel::class.java
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = MainFragmentBinding.inflate(inflater)

        initializeRecyclerView()
        setObservers()

        return binding.root
    }

    private fun setObservers() {

        viewModel.getObservableNewsItems().observe(this, Observer { list ->
            articlesAdapter.submitList(list)
        })

        viewModel.getObservableUrl().observe(this, Observer { url ->
            url?.let {

                val bundle = Bundle()
                bundle.putString("url", it)
                findNavController().navigate(R.id.webViewContainer, bundle)
            }
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
        viewModel.onCellClicked(url)
    }

    override fun onResume() {
        viewModel.onResume()
        super.onResume()
    }
}
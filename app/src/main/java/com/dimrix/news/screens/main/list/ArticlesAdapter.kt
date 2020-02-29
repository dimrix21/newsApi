package com.dimrix.news.screens.main.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dimrix.news.data.local.db.item.ArticleItemEntry
import com.dimrix.news.data.network.enteties.NewsApiResponse
import com.dimrix.news.databinding.ListItemArticleBinding

class ArticlesAdapter(private val cellClickCallback: (String) -> (Unit)) :
    ListAdapter<ArticleItemEntry, ArticleViewHolder>(DefaultDiffChecker()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {

        return ArticleViewHolder(
            ListItemArticleBinding.inflate(LayoutInflater.from(parent.context)),
            cellClickCallback
        )

    }


    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(getItem(position))

    }
}


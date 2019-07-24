package com.dimrix.news.screens.main.list

import androidx.recyclerview.widget.DiffUtil
import com.dimrix.news.data.network.enteties.NewsApiResponse.Article

/**
 * Allows the RecyclerView to determine which items have changed when the [List] of [Article]
 * has been updated.
 */
class DefaultDiffChecker : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

}
package com.dimrix.news.screens.main.list

import androidx.recyclerview.widget.DiffUtil
import com.dimrix.news.data.local.db.item.ArticleItemEntry

/**
 * Allows the RecyclerView to determine which items have changed when the [List] of [ArticleItemEntry]
 * has been updated.
 */
class DefaultDiffChecker : DiffUtil.ItemCallback<ArticleItemEntry>() {
    override fun areItemsTheSame(oldItem: ArticleItemEntry, newItem: ArticleItemEntry): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(oldItem: ArticleItemEntry, newItem: ArticleItemEntry): Boolean {
        return oldItem == newItem
    }

}
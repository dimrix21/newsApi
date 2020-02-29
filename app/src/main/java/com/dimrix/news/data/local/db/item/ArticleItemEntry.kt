package com.dimrix.news.data.local.db.item

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleItemEntry(
    @PrimaryKey(autoGenerate = true) var id: Int = -1,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
   @Nullable val urlToImage: String
)


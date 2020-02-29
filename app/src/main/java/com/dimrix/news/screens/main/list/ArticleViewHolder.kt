package com.dimrix.news.screens.main.list

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dimrix.news.R
import com.dimrix.news.data.local.db.item.ArticleItemEntry
import com.dimrix.news.databinding.ListItemArticleBinding

class ArticleViewHolder(
    private var binding: ListItemArticleBinding,
    private val cellClickCallback: (String) -> (Unit)
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(article: ArticleItemEntry) {
        binding.article = article
        bindImage(binding.listItemIv, article.urlToImage)
        binding.executePendingBindings()

        itemView.setOnClickListener {
            cellClickCallback(article.url)
        }

    }

    private fun bindImage(imgView: ImageView, imgUrl: String?) {
        imgUrl?.let {
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
            Glide.with(imgView.context)
                .load(imgUri)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image)
                )
                .into(imgView)
        }
    }


}
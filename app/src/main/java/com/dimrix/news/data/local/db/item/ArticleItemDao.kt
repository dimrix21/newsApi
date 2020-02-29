package com.dimrix.news.data.local.db.item

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.dimrix.news.data.local.db.BaseDao


@Dao
interface ArticleItemDao : BaseDao<ArticleItemEntry> {

    @Query("SELECT * FROM ArticleItemEntry ORDER BY publishedAt DESC")
    fun getAll(): LiveData<List<ArticleItemEntry>>

    @Query("DELETE FROM ArticleItemEntry")
    suspend fun deleteAll()

    @Transaction
    suspend fun replaceAllData(vararg obj: ArticleItemEntry) {
        deleteAll()
        bulkInsert(*obj)
    }


}
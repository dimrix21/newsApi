package com.dimrix.news.data.local.db

import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bulkInsert(vararg obj: T)

}
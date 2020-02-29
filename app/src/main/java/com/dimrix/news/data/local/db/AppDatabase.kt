package com.dimrix.news.data.local.db


import android.content.Context
import androidx.room.*
import com.dimrix.news.data.local.db.item.ArticleItemDao
import com.dimrix.news.data.local.db.item.ArticleItemEntry
import java.util.*


@Database(
    entities = [ArticleItemEntry::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(AppDatabase.Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleItemDao(): ArticleItemDao


    companion object {
        private var INSTANCE: AppDatabase? = null


        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {

                    // prepopulate the database after onCreate was called
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "myDB"
                    ).build()
                }

            }
            return INSTANCE
        }

    }


    object Converters {
        @TypeConverter
        @JvmStatic
        fun fromTimestamp(value: Long?): Calendar? = value?.let { value ->
            GregorianCalendar().also { calendar ->
                calendar.timeInMillis = value
            }
        }

        @TypeConverter
        @JvmStatic
        fun toTimestamp(timestamp: Calendar?): Long? = timestamp?.timeInMillis

        @TypeConverter
        @JvmStatic
        fun fromTimestampToDate(value: Long?): Date? {
            return if (value == null) null else Date(value)
        }

        @TypeConverter
        @JvmStatic
        fun dateToTimestamp(date: Date?): Long? {
            if (date != null && date.time != null) {
                return date?.time?.toLong()
            } else {
                return 0
            }
        }
    }


}




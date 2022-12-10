package com.example.nontonime.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nontonime.entity.BookmarkEntity
import com.example.nontonime.response.DataResponseItem


@Database(entities = [BookmarkEntity::class], version = 2)
abstract class BookmarkDatabase : RoomDatabase() {

    abstract fun movieDao() : MovieDao

    companion object {
        @Volatile
        private var INSTANCE: BookmarkDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context) : BookmarkDatabase {
            if (INSTANCE == null) {
                synchronized(BookmarkDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        BookmarkDatabase::class.java,
                        "bookmark_database"
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE as BookmarkDatabase
        }
    }
}
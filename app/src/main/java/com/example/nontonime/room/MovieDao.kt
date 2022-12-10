package com.example.nontonime.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.nontonime.entity.BookmarkEntity
import com.example.nontonime.response.DataResponse
import com.example.nontonime.response.DataResponseItem

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie : BookmarkEntity)

    @Delete
    fun delete(movie : BookmarkEntity)

    @Query("SELECT * FROM movieTable")
    fun getAllData() : LiveData<List<BookmarkEntity>>

    @Query("SELECT COUNT(1) FROM movieTable WHERE animeid = :animeid")
    fun isBookmark(animeid: String): LiveData<Int>


    @Update
    suspend fun edit(movie: BookmarkEntity)

}
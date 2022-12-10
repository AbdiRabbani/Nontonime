package com.example.nontonime.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nontonime.response.DataResponseItem

@Entity(tableName = "movieTable")
data class BookmarkEntity (
    @PrimaryKey
    @ColumnInfo(name = "animeid")
    val animeid : String,

    @ColumnInfo(name = "releasedDate")
    val releasedDate: String?,

    @ColumnInfo(name = "animeImg")
    val animeImg: String,

    @ColumnInfo(name = "animeTitle")
    val animeTitle: String
) {
    fun asBookmarkResponse() = DataResponseItem(
        animeId = animeid,
        animeTitle = animeTitle,
        animeImg = animeImg,
        releasedDate = releasedDate
    )
}
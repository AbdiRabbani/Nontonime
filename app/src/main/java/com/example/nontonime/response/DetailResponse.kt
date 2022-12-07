package com.example.nontonime.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailResponseItem(

    @field:SerializedName("releasedDate")
    val releasedDate: String? = null,

    @field:SerializedName("otherNames")
    val otherNames: String? = null,

    @field:SerializedName("animeImg")
    val animeImg: String? = null,

    @field:SerializedName("totalEpisodes")
    val totalEpisodes: String? = null,

    @field:SerializedName("genres")
    val genres: List<String?>? = null,

    @field:SerializedName("synopsis")
    val synopsis: String? = null,

    @field:SerializedName("animeTitle")
    val animeTitle: String? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("episodesList")
    val episodesList: ArrayList<EpisodeListItem?>? = null,

) : Parcelable

@Parcelize
data class EpisodeListItem(

    @field:SerializedName("episodeId")
    val episodeId: String? = null,

    @field:SerializedName("episodeNum")
    val episodeNum: String? = null,

    @field:SerializedName("episodeUrl")
    val episodeUrl: String? = null

) : Parcelable

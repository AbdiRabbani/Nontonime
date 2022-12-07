package com.example.nontonime.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataResponse(

	@field:SerializedName("DataResponse")
	val dataResponse: List<DataResponseItem?>? = null
) : Parcelable

@Parcelize
data class DataResponseItem(

	@field:SerializedName("releasedDate")
	val releasedDate: String? = null,

	@field:SerializedName("animeUrl")
	val animeUrl: String? = null,

	@field:SerializedName("animeId")
	val animeId: String? = null,

	@field:SerializedName("animeImg")
	val animeImg: String? = null,

	@field:SerializedName("animeTitle")
	val animeTitle: String? = null,

	@field:SerializedName("episodeNum")
	val episodeNum: String? = null
) : Parcelable

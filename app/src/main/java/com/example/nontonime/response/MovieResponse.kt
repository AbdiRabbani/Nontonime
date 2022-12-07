package com.example.nontonime.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponseItem(

	@field:SerializedName("sources")
	val sources: List<SourcesItem?>? = null,

	@field:SerializedName("Referer")
	val referer: String? = null,

	@field:SerializedName("sources_bk")
	val sourcesBk: List<SourcesBkItem?>? = null
) : Parcelable

@Parcelize
data class SourcesBkItem(

	@field:SerializedName("file")
	val file: String? = null,

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("type")
	val type: String? = null
) : Parcelable

@Parcelize
data class SourcesItem(

	@field:SerializedName("file")
	val file: String? = null,

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("type")
	val type: String? = null
) : Parcelable

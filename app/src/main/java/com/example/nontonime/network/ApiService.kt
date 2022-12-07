package com.example.nontonime.network

import com.example.nontonime.response.DataResponseItem
import com.example.nontonime.response.DetailResponseItem
import com.example.nontonime.response.MovieResponseItem
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    fun getSearch(
        @Query("keyw") movieName: String?
    ): Flowable<List<DataResponseItem>>

    @GET("popular")
    fun getPopular(): Flowable<List<DataResponseItem>>

    @GET("anime-movies")
    fun getAll(): Flowable<List<DataResponseItem>>

    @GET("top-airing")
    fun getTopAiring(): Flowable<List<DataResponseItem>>

    @GET("recent-release")
    fun getRecentRelease(): Flowable<List<DataResponseItem>>

    @GET("anime-details/{idMovie}")
    fun getDetail(
        @Path("idMovie") idMovie: String?
    ): Flowable<DetailResponseItem>

    @GET("vidcdn/watch/{idMovie}-episode-{noEps}")
    fun getMovie(
        @Path("idMovie") idMovie: String?,
        @Path("noEps") noEps: String?
    ): Flowable<MovieResponseItem>
}
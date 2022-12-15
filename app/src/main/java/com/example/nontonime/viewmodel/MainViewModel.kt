package com.example.nontonime.viewmodel

import android.app.Application
import android.provider.ContactsContract.Data
import androidx.lifecycle.*
import com.example.nontonime.entity.BookmarkEntity
import com.example.nontonime.network.ApiClient
import com.example.nontonime.response.DataResponseItem
import com.example.nontonime.response.DetailResponseItem
import com.example.nontonime.room.BookmarkDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val dataResponse = MutableLiveData<List<DataResponseItem>>()
    val dataSearchResponse = MutableLiveData<List<DataResponseItem>>()
    val dataAiringResponse = MutableLiveData<List<DataResponseItem>>()
    val dataMovieResponse = MutableLiveData<List<DataResponseItem>>()
    val dataRecentResponse = MutableLiveData<List<DataResponseItem>>()
    val dataDetailResponse = MutableLiveData<DetailResponseItem>()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Throwable>()

    private fun getPopularMovie(
        responseHandlerMovie: (List<DataResponseItem>) -> Unit,
        error: (Throwable) -> Unit
    ) {

        ApiClient.getApiService().getPopular().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                responseHandlerMovie(it)
            }, {
                error(it)
            })
    }


    fun getDataPopular() {
        isLoading.value = true
        getPopularMovie({
            isLoading.value = false
            dataResponse.value = it
        }, {
            isLoading.value = false
            isError.value = it
        })
    }

    private fun getDetailrMovie(
        idMovie: String?,
        responseHandlerMovie: (DetailResponseItem) -> Unit,
        error: (Throwable) -> Unit
    ) {

        ApiClient.getApiService().getDetail(idMovie).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                responseHandlerMovie(it)
            }, {
                error(it)
            })
    }


    fun getDataDetail(idMovie: String?) {
        isLoading.value = true
        getDetailrMovie(
            idMovie, {
                isLoading.value = false
                dataDetailResponse.value = it
            }, {
                isLoading.value = false
                isError.value = it
            })
    }

    private fun getRecentMovie(
        responseHandlerMovie: (List<DataResponseItem>) -> Unit,
        error: (Throwable) -> Unit
    ) {

        ApiClient.getApiService().getRecentRelease().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                responseHandlerMovie(it)
            }, {
                error(it)
            })
    }


    fun getDataRecent() {
        isLoading.value = true
        getRecentMovie({
            isLoading.value = false
            dataRecentResponse.value = it
        }, {
            isLoading.value = false
            isError.value = it
        })
    }

    private fun getAllMovie(
        responseHandlerMovie: (List<DataResponseItem>) -> Unit,
        error: (Throwable) -> Unit
    ) {

        ApiClient.getApiService().getAll().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                responseHandlerMovie(it)
            }, {
                error(it)
            })
    }


    fun getDataAll() {
        isLoading.value = true
        getAllMovie({
            isLoading.value = false
            dataMovieResponse.value = it
        }, {
            isLoading.value = false
            isError.value = it
        })
    }

    private fun getAiringMovie(
        responseHandlerMovie: (List<DataResponseItem>) -> Unit,
        error: (Throwable) -> Unit
    ) {

        ApiClient.getApiService().getTopAiring().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                responseHandlerMovie(it)
            }, {
                error(it)
            })
    }


    fun getDataAiring() {
        isLoading.value = true
        getAiringMovie({
            isLoading.value = false
            dataAiringResponse.value = it
        }, {
            isLoading.value = false
            isError.value = it
        })
    }

    private fun getSearchMovie(
        movieName : String?,
        responseHandlerMovie: (List<DataResponseItem>) -> Unit,
        error: (Throwable) -> Unit
    ) {

        ApiClient.getApiService().getSearch(movieName).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                responseHandlerMovie(it)
            }, {
                error(it)
            })
    }


    fun getDataSearch(movieName: String?) {
        isLoading.value = true
        getSearchMovie(
            movieName,{
                isLoading.value = false
                dataSearchResponse.value = it
            }, {
                isLoading.value = false
                isError.value = it
            })
    }

   //Room

    private val movieDao = BookmarkDatabase.getDatabase(application).movieDao()


    fun bookmarkMovie(movie: DataResponseItem) {
        viewModelScope.launch(Dispatchers.IO) {
            movieDao.insert(movie.asBookmarkEntity())
        }
    }

    fun unBookmarkMovie(movie: DataResponseItem) {
        viewModelScope.launch(Dispatchers.IO) {
            movieDao.delete(movie.asBookmarkEntity())
        }
    }

    fun isMovieBookmarked(movie: DataResponseItem): LiveData<Boolean> {
        return Transformations.map(movieDao.isBookmark(movie.animeId as String)) {
            it == 1
        }
    }

    fun getAllBookmarkedMovie(): LiveData<List<DataResponseItem>> {
        return Transformations.map(movieDao.getAllData()) { list ->
            list.map { it.asBookmarkResponse() }
        }
    }


}
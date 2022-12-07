package com.example.nontonime.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nontonime.network.ApiClient
import com.example.nontonime.response.DataResponseItem
import com.example.nontonime.response.DetailResponseItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel : ViewModel() {

    val dataResponse = MutableLiveData<List<DataResponseItem>>()
    val dataDetailResponse = MutableLiveData<DetailResponseItem>()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Throwable>()

    fun getPopularMovie(
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

    fun getDetailrMovie(
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

    fun getWatchMovie(
        idMovie: String?,
        noEps: String?,
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


    fun getDataWatch(idMovie: String?, noEps: String?) {
        isLoading.value = true
        getDetailrMovie(
            idMovie, {
                isLoading.value = false
                dataDetailResponse.value = it
            }, {
                isLoading.value = false
                isError.value = it
            })
        getDetailrMovie(
            noEps, {
                isLoading.value = false
                dataDetailResponse.value = it
            }, {
                isLoading.value = false
                isError.value = it
            })
    }

}
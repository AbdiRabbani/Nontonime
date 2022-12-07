package com.example.nontonime.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nontonime.network.ApiClient
import com.example.nontonime.response.DataResponse
import com.example.nontonime.response.DataResponseItem
import com.example.nontonime.response.DetailResponseItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchViewModel : ViewModel() {

    val dataResponse = MutableLiveData<List<DataResponseItem>>()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Throwable>()


    fun getSearchMovie(
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
            dataResponse.value = it
        }, {
            isLoading.value = false
            isError.value = it
        })
    }

}
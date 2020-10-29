package com.example.searchmovie.observer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchmovie.data.dataSource.local.LocalDataSourceImpl
import com.example.searchmovie.data.dataSource.remote.RemoteDataSourceImpl
import com.example.searchmovie.data.model.Items
import com.example.searchmovie.data.repository.MovieSearchRepositoryImpl

class MainViewModel : ViewModel() {
    private val repository =
        MovieSearchRepositoryImpl(LocalDataSourceImpl(), RemoteDataSourceImpl())
    val query = MutableLiveData<String>()
    val movieList = MutableLiveData<List<Items>>()
    val showToast = MutableLiveData<Boolean>(false)
    val showDialog = MutableLiveData<Boolean>()
    var queryState = ""
    val queryList = MutableLiveData<List<String>>()

    fun callMovieList() {
        val query = query.value
        if (query.isNullOrBlank()) {
            queryState = "empty"
            showToast.value = true
            movieList.value = arrayListOf()
        } else {
            repository.callMovieList(query, {
                if (it.isEmpty()) {
                    queryState = "result_empty"
                    showToast.value = true
                    movieList.value = arrayListOf()
                } else {
                    queryState = "success"
                    showToast.value = true
                    movieList.value = it
                }
            }, {
                queryState = "error"
                showToast.value = true
            })
        }
    }

    fun callDialog() {
        showDialog.value = true
    }

    fun callQueryList() {
        queryList.value = repository.readRecentQuery()
    }

    fun dismissDialog() {
        showDialog.value = false
    }

}

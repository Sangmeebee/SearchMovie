package com.example.searchmovie.observer

import androidx.databinding.ObservableField
import com.example.searchmovie.data.dataSource.local.LocalDataSourceImpl
import com.example.searchmovie.data.dataSource.remote.RemoteDataSourceImpl
import com.example.searchmovie.data.model.Items
import com.example.searchmovie.data.repository.MovieSearchRepositoryImpl

class MainViewModel {
    private val repository =
        MovieSearchRepositoryImpl(LocalDataSourceImpl(), RemoteDataSourceImpl())
    val query = ObservableField<String>()
    val movieList = ObservableField<List<Items>>()
    val showToast = ObservableField<Unit>()
    var queryState = ""

    fun callMovieList() {
        val query = query.get()
        if (query.isNullOrBlank()) {
            queryState = "empty"
            showToast.notifyChange()
            movieList.set(arrayListOf())
        } else {
            repository.callMovieList(query, {
                if (it.isEmpty()) {
                    queryState = "result_empty"
                    showToast.notifyChange()
                    movieList.set(arrayListOf())
                } else {
                    queryState = "success"
                    showToast.notifyChange()
                    movieList.set(it)
                }
            }, {
                queryState = "error"
                showToast.notifyChange()
                showToast.notifyChange()
            })
        }
    }
}

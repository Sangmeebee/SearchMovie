package com.example.searchmovie.observer

import androidx.databinding.ObservableField
import com.example.searchmovie.data.dataSource.local.LocalDataSourceImpl
import com.example.searchmovie.data.dataSource.remote.RemoteDataSourceImpl
import com.example.searchmovie.data.repository.MovieSearchRepositoryImpl

class QueryViewModel {
    private val repository =
        MovieSearchRepositoryImpl(LocalDataSourceImpl(), RemoteDataSourceImpl())
    val query = ObservableField<String>()
    val queryList = ObservableField<List<String>>()
    val isVisible = ObservableField<Unit>()

    fun callQueryList() = queryList.set(repository.readRecentQuery())


    fun dismissDialog() {
        isVisible.notifyChange()
    }
}

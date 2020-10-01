package com.example.searchmovie.data.repository

import com.example.searchmovie.data.dataSource.local.LocalDataSource
import com.example.searchmovie.data.dataSource.remote.RemoteDataSource
import com.example.searchmovie.data.model.Items

class MovieSearchRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : MovieSearchRepository {
    override fun callMovieList(
        query: String,
        success: (ArrayList<Items>) -> Unit,
        failed: (String) -> Unit
    ) {
        remoteDataSource.callMovieList(query, success, failed)
    }

}

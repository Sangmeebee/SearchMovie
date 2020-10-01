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
        remoteDataSource.callMovieList(query, {
            success(it)
            //최근 검색 목록 sharedPreferences에 저장
            saveRecentQuery(query)
        }, failed)
    }

    override fun saveRecentQuery(query: String) {
        localDataSource.saveRecentQuery(query)
    }

    override fun readRecentQuery(): ArrayList<String> = localDataSource.readRecentQuery()

}

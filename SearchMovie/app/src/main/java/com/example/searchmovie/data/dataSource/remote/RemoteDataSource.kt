package com.example.searchmovie.data.dataSource.remote

import com.example.searchmovie.data.model.Items

interface RemoteDataSource {

    fun callMovieList(
        query: String,
        success: (ArrayList<Items>) -> Unit,
        failed: (String) -> Unit
    )
}

package com.example.searchmovie.data.repository

import com.example.searchmovie.data.model.Items

interface MovieSearchRepository {

    fun callMovieList(
        query: String,
        success: (ArrayList<Items>) -> Unit,
        failed: (String) -> Unit
    )

    fun saveRecentQuery(query: String)
    fun readRecentQuery(): ArrayList<String>
}

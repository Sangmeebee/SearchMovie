package com.example.searchmovie.data.dataSource.local

interface LocalDataSource {
    fun saveRecentQuery(query: String)
    fun readRecentQuery(): ArrayList<String>
}

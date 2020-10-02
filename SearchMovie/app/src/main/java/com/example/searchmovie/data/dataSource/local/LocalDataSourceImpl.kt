package com.example.searchmovie.data.dataSource.local

import com.example.searchmovie.data.App

class LocalDataSourceImpl : LocalDataSource {
    override fun saveRecentQuery(query: String) {
        App.prefs.saveRecentQuery(query)
    }

    override fun readRecentQuery() = App.prefs.readRecentQuery()
}

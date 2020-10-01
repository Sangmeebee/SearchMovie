package com.example.searchmovie.data.dataSource.local

import com.example.searchmovie.data.App

class LocalDataSourceImpl : LocalDataSource {
    override fun saveRecentQuery(query: String) {
        App.prefs.saveRecentQuery(query)
    }

    override fun readRecentQuery(): ArrayList<String> {
        val queryList = App.prefs.readRecentQuery()
        val temp = arrayListOf<String>()
        for(i in queryList.size -1  downTo 0){
            temp.add(queryList[i])
        }
        return temp
    }
}

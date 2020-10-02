package com.example.searchmovie.data

import android.app.Activity
import android.content.Context
import org.json.JSONArray

class Preferences(context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)

    fun saveRecentQuery(query: String) {
        val queryList = readRecentQuery()
        queryList.add(query)
        if (queryList.size > 5) {
            queryList.removeAt(0)
        }
        val jsonArray = JSONArray(queryList).toString()
        sharedPreferences.edit().putString("search_list", jsonArray).apply()
    }

    fun readRecentQuery(): ArrayList<String> {
        val jsonString = sharedPreferences.getString("search_list", null)
        val queryList = arrayListOf<String>()
        jsonString?.let {
            val jsonArray = JSONArray(it)
            for (i in 0 until jsonArray.length()) {
                queryList.add(jsonArray[i].toString())
            }
        }
        return queryList
    }
}

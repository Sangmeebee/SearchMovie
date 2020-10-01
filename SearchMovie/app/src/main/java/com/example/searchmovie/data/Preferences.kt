package com.example.searchmovie.data

import android.app.Activity
import android.content.Context
import org.json.JSONArray

class Preferences(context: Context) {
    private val preferences =
        context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)

    fun saveRecentQuery(query: String) {
        val dataList = readRecentQuery()
        dataList.add(query)
        if (dataList.size > 5) {
            dataList.removeAt(0)
        }
        preferences.edit().putString(CALLING_RECENT_QUERY, JSONArray(dataList).toString()).apply()
    }

    fun readRecentQuery(): ArrayList<String> {
        val jsonString = preferences.getString(CALLING_RECENT_QUERY, null)
        val dataList = arrayListOf<String>()
        jsonString?.let {
            val jsonArray = JSONArray(it)
            for (i in 0 until jsonArray.length()) {
                dataList.add(jsonArray[i].toString())
            }
        }
        return dataList
    }

    companion object {
        const val CALLING_RECENT_QUERY = "recent_query"
    }
}

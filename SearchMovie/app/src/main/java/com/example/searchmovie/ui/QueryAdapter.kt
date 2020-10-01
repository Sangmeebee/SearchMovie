package com.example.searchmovie.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.searchmovie.R
import com.example.searchmovie.data.model.Items

class QueryAdapter :
    RecyclerView.Adapter<QueryAdapter.QueryViewHolder>() {

    private val queryList = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.query_list, parent, false)
        return QueryViewHolder(view)
    }

    override fun onBindViewHolder(holder: QueryViewHolder, position: Int) {
        holder.bind(queryList[position])
    }

    override fun getItemCount() = queryList.size

    fun clearAndAddQuery(query: ArrayList<String>) {
        queryList.clear()
        queryList.addAll(query)
        notifyDataSetChanged()
    }

    class QueryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvQuery = view.findViewById<TextView>(R.id.tv_query)
        fun bind(query: String) {
            tvQuery.text = query
        }
    }
}

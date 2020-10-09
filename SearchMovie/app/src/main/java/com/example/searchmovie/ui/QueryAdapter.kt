package com.example.searchmovie.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.searchmovie.R
import com.example.searchmovie.databinding.QueryListBinding

class QueryAdapter(private val setQuery: (String) -> Unit) : RecyclerView.Adapter<QueryAdapter.QueryViewHolder>() {

    private val queryList = arrayListOf<String>()
    private lateinit var binding: QueryListBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueryViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.query_list, parent, false)
        binding.apply {
            adapter = this@QueryAdapter
        }
        return QueryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QueryViewHolder, position: Int) {
        holder.bind(queryList[position])
    }

    override fun getItemCount() = queryList.size

    fun clearAndAddQuery(query: List<String>) {
        queryList.clear()
        queryList.addAll(query)
        notifyDataSetChanged()
    }

    fun setSelectedQuery(query: String){
        setQuery(query)
    }

    class QueryViewHolder(private val binding: QueryListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(query: String) {
            binding.query = query
            binding.tvQuery.text = query
        }
    }
}

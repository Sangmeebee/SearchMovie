package com.example.searchmovie.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.searchmovie.R
import com.example.searchmovie.data.model.Items
import com.example.searchmovie.databinding.MovieItemBinding

class MovieAdapter :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val movieList = arrayListOf<Items>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<MovieItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.movie_item,
            parent,
            false
        )
        val result = ViewHolder(binding)
        binding.root.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(movieList[result.bindingAdapterPosition].link)
            parent.context.startActivity(intent)
        }
        return result
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size

    fun clearAndAddItems(items: List<Items>) {
        movieList.clear()
        movieList.addAll(items)
        notifyDataSetChanged()
    }

    fun clearItems() {
        movieList.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Items) {
            binding.item = item
        }
    }
}

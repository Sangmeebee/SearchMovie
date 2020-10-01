package com.example.searchmovie

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.searchmovie.data.Items
import com.example.searchmovie.data.Movie
import com.example.searchmovie.service.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val movieAdapter = MovieAdapter(this, arrayListOf<Items>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_movie.apply {
            adapter = movieAdapter
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }

        btn_search.setOnClickListener {
            val query = et_query.text.toString()
            if (query.isEmpty()) {
                showMessage(getString(R.string.no_word))
            }
            else{
                rv_movie.layoutManager?.scrollToPosition(0)
                callMovieList(query)
            }
        }
    }

    private fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun callMovieList(query: String) {
        RetrofitClient.getService().getMovieList(query).enqueue(object :
            Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {

                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        val items = ArrayList<Items>(it.items)
                        if (items.isEmpty()) {
                            movieAdapter.clearItems()
                            showMessage(getString(R.string.no_result))
                        } else {
                            movieAdapter.clearAndAddItems(items)
                        }
                    }
                } else {
                    showMessage(getString(R.string.net_error))
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                showMessage(t.message.toString())
            }
        })
    }
}

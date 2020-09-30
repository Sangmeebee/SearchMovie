package com.example.searchmovie

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.searchmovie.data.Movie
import com.example.searchmovie.service.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_search.setOnClickListener {
            val query = et_query.text.toString()

            RetrofitClient.getService().getMovieList(query).enqueue(object :
                Callback<Movie> {
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    Log.d("sangmee", response.body().toString())
                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                }
            })
        }
    }
}

package com.example.searchmovie.data.dataSource.remote

import com.example.searchmovie.data.model.Items
import com.example.searchmovie.data.model.Movie
import com.example.searchmovie.data.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSourceImpl : RemoteDataSource {
    override fun callMovieList(
        query: String,
        success: (ArrayList<Items>) -> Unit,
        failed: (String) -> Unit
    ) {
        RetrofitClient.getService().getMovieList(query).enqueue(object :
            Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {

                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        success(it.items)
                    }
                } else {
                    failed(response.message())
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                failed(t.message.toString())
            }
        })
    }
}

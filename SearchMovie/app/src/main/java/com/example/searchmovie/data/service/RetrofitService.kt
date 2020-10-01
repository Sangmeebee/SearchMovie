package com.example.searchmovie.data.service

import com.example.searchmovie.data.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitService {

    @Headers("X-Naver-Client-Id: $NAVER_CLIENT_ID", "X-Naver-Client-Secret: $NAVER_CLIENT_SECRET")
    @GET("movie.json")
    fun getMovieList(@Query("query") query: String): Call<Movie>
}

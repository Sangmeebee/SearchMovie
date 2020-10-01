package com.example.searchmovie.data.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://openapi.naver.com/v1/search/"
const val NAVER_CLIENT_ID = "gtKJPFNgyeGrlp2Ftz2F"
const val NAVER_CLIENT_SECRET = "2oAqr314Pl"
object RetrofitClient{
    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getService() = retrofit.create(RetrofitService::class.java)
}

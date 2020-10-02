package com.example.searchmovie.ui.main

import com.example.searchmovie.data.model.Items
import com.example.searchmovie.ui.base.BaseContract

interface MainContract {
    interface View : BaseContract.View {
        fun initScroll()
        fun showEmptyMsg()
        fun showNoResult()
        fun showResult(movieList: ArrayList<Items>)

    }

    interface Presenter : BaseContract.Presenter {
        fun searchMovieList(query: String)
    }
}

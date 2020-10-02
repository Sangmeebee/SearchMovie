package com.example.searchmovie.ui.main

import com.example.searchmovie.data.repository.MovieSearchRepository
import com.example.searchmovie.ui.base.BaseContract

class MainPresenter(
    private val view: MainContract.View,
    private val movieSearchRepository: MovieSearchRepository
) :
    BaseContract.Presenter, MainContract.Presenter {
    override fun searchMovieList(query: String) {
        if (query.isEmpty()) {
            view.showEmptyMsg()
        } else {
            movieSearchRepository.callMovieList(query, {
                if (it.isEmpty()) {
                    view.showNoResult()
                } else {
                    view.showResult(it)
                }
            }, { view.showMessage(it) })
        }
    }
}



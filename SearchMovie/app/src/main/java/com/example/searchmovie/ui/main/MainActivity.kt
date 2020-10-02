package com.example.searchmovie.ui.main

import android.os.Bundle
import android.text.Editable
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.searchmovie.R
import com.example.searchmovie.data.dataSource.local.LocalDataSourceImpl
import com.example.searchmovie.data.dataSource.remote.RemoteDataSourceImpl
import com.example.searchmovie.data.model.Items
import com.example.searchmovie.data.repository.MovieSearchRepository
import com.example.searchmovie.data.repository.MovieSearchRepositoryImpl
import com.example.searchmovie.databinding.ActivityMainBinding
import com.example.searchmovie.ui.MovieAdapter
import com.example.searchmovie.ui.OnListItemSelectedListener
import com.example.searchmovie.ui.RecentQueryDialog
import com.example.searchmovie.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : OnListItemSelectedListener,
    BaseActivity<MainContract.Presenter, ActivityMainBinding>(R.layout.activity_main),
    MainContract.View {

    private val movieAdapter = MovieAdapter(this, arrayListOf<Items>())
    private val movieSearchRepository: MovieSearchRepository by lazy {
        MovieSearchRepositoryImpl(
            LocalDataSourceImpl(), RemoteDataSourceImpl()
        )
    }

    override val presenter by lazy {
        MainPresenter(this, movieSearchRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activity = this

        rv_movie.apply {
            adapter = movieAdapter
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }
    }

    fun searchMovieList() {
        val query = binding.etQuery.text.toString()
        presenter.searchMovieList(query)
    }

    fun showQueryDialog() {
        val dialog =
            RecentQueryDialog.newInstance(movieSearchRepository.readRecentQuery())
        dialog.show(supportFragmentManager, "query_dialog")
    }

    override fun initScroll() {
        binding.rvMovie.layoutManager?.scrollToPosition(0)
    }

    override fun showEmptyMsg() {
        showMessage(getString(R.string.no_word))
    }

    override fun showNoResult() {
        showMessage(getString(R.string.no_result))
        movieAdapter.clearItems()
    }

    override fun showResult(movieList: ArrayList<Items>) {
        movieAdapter.clearAndAddItems(movieList)
    }

    override fun selectedItem(query: String) {
        binding.etQuery.text = Editable.Factory.getInstance().newEditable(query)
    }

}

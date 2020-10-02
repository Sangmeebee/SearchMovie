package com.example.searchmovie.ui.main

import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.searchmovie.R
import com.example.searchmovie.data.dataSource.local.LocalDataSourceImpl
import com.example.searchmovie.data.dataSource.remote.RemoteDataSourceImpl
import com.example.searchmovie.data.model.Items
import com.example.searchmovie.data.repository.MovieSearchRepository
import com.example.searchmovie.data.repository.MovieSearchRepositoryImpl
import com.example.searchmovie.ui.MovieAdapter
import com.example.searchmovie.ui.OnListItemSelectedListener
import com.example.searchmovie.ui.RecentQueryDialog
import com.example.searchmovie.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : OnListItemSelectedListener,
    BaseActivity<MainContract.Presenter>(R.layout.activity_main), MainContract.View {

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

        rv_movie.apply {
            adapter = movieAdapter
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }

        btn_search.setOnClickListener {
            val query = et_query.text.toString()
            if (query.isEmpty()) {
                showMessage(getString(R.string.no_word))
            } else {
                rv_movie.layoutManager?.scrollToPosition(0)

                //repository로 movieList 호출
                movieSearchRepository.callMovieList(query, {
                    if (it.isEmpty()) {
                        showMessage(getString(R.string.no_result))
                        movieAdapter.clearItems()
                    } else {
                        movieAdapter.clearAndAddItems(it)
                    }
                }, { showMessage(it) })
            }
        }

        btn_history.setOnClickListener {
            // queryDialog 출력
            val dialog =
                RecentQueryDialog.newInstance(movieSearchRepository.readRecentQuery())
            dialog.show(supportFragmentManager, "query_dialog")
        }
    }

    private fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun selectedItem(query: String) {
        et_query.text = Editable.Factory.getInstance().newEditable(query)
    }

}

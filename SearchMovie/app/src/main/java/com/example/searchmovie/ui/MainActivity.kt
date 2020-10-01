package com.example.searchmovie.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.searchmovie.R
import com.example.searchmovie.data.dataSource.local.LocalDataSourceImpl
import com.example.searchmovie.data.dataSource.remote.RemoteDataSourceImpl
import com.example.searchmovie.data.model.Items
import com.example.searchmovie.data.repository.MovieSearchRepository
import com.example.searchmovie.data.repository.MovieSearchRepositoryImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val movieAdapter = MovieAdapter(this, arrayListOf<Items>())
    private val movieSearchRepository: MovieSearchRepository by lazy {
        MovieSearchRepositoryImpl(
            LocalDataSourceImpl(), RemoteDataSourceImpl()
        )
    }

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

}

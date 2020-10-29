package com.example.searchmovie.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.searchmovie.R
import com.example.searchmovie.databinding.ActivityMainBinding
import com.example.searchmovie.extension.toast
import com.example.searchmovie.observer.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val movieAdapter = MovieAdapter()
    private lateinit var binding: ActivityMainBinding
    private val vm by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.vm = vm

        observeCallback()

        rv_movie.apply {
            adapter = movieAdapter
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }
    }

    private fun observeCallback() {

        vm.movieList.observe(this, Observer {
            vm.movieList.value?.let {
                if (it.isEmpty()) {
                    movieAdapter.clearItems()
                } else {
                    movieAdapter.clearAndAddItems(it)
                }
            }
        })

        vm.showToast.observe(this, Observer {
            if(it){
                when (vm.queryState) {
                    "empty" -> toast(R.string.no_word)
                    "success" -> toast(R.string.success)
                    "error" -> toast(R.string.net_error)
                    "result_empty" -> toast(R.string.no_result)
                }
            }
        })

        vm.showDialog.observe(this, Observer {
            if(it){
                showTitleDialog()
            }
        })
    }

    private fun showTitleDialog() {
        RecentQueryDialog().show(supportFragmentManager, "title_history_dialog")
    }
}

package com.example.searchmovie.ui

import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.searchmovie.R
import com.example.searchmovie.databinding.ActivityMainBinding
import com.example.searchmovie.extension.toast
import com.example.searchmovie.observer.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : OnListItemSelectedListener, AppCompatActivity() {

    private val movieAdapter = MovieAdapter()
    private lateinit var binding: ActivityMainBinding
    private val vm = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = vm

        observeCallback()

        rv_movie.apply {
            adapter = movieAdapter
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }
    }

    private fun observeCallback() {
        vm.movieList.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                vm.movieList.get()?.let {
                    if (it.isEmpty()) {
                        movieAdapter.clearItems()
                    } else {
                        movieAdapter.clearAndAddItems(it)
                    }
                }
            }
        })

        vm.showToast.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                when (vm.queryState) {
                    "empty" -> toast(R.string.no_word)
                    "success" -> toast(R.string.success)
                    "error" -> toast(R.string.net_error)
                    "result_empty" -> toast(R.string.no_result)
                }
            }
        })

        vm.showDialog.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                showTitleDialog()
            }
        })
    }

    override fun selectedItem(query: String) {
        binding.etQuery.text = Editable.Factory.getInstance().newEditable(query)
    }

    private fun showTitleDialog() {
        val titleDialog = RecentQueryDialog()
        titleDialog.show(supportFragmentManager, "title_history_dialog")
    }
}

package com.example.searchmovie.ui.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<T : BaseContract.Presenter>(@LayoutRes val layout: Int) :
    BaseContract.View, AppCompatActivity(layout) {

    abstract val presenter: T
}

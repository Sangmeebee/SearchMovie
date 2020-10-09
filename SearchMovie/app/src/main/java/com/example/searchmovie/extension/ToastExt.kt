package com.example.searchmovie.extension

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.toast(@StringRes stringId: Int) {
    Toast.makeText(this, getString(stringId), Toast.LENGTH_SHORT).show()
}

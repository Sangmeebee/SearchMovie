package com.example.searchmovie.extension

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String) {
    Glide.with(this).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(this)
}

@BindingAdapter("webLink")
fun View.showWebView(link: String) {
    setOnClickListener {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(link)
        context.startActivity(intent)
    }
}

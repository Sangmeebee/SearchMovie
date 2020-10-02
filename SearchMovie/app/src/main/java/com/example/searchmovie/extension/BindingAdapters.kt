package com.example.searchmovie.extension

import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String) {
    Glide.with(this).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(this)
}

@BindingAdapter("rating")
fun RatingBar.setRating(rating: Double){
    setRating(rating.toFloat()/2)
}

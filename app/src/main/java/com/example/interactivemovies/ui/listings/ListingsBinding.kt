package com.example.interactivemovies.ui.listings

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.orhanobut.logger.Logger
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso


@BindingAdapter("movieTitle")
fun bindTextTitle(textView: TextView, title: String) {
    textView.text = title
}

@BindingAdapter("moviePoster")
fun bindImage(imageView: ImageView, imageUrl: String?) {

    Logger.d(imageUrl)
    Picasso.get()
        .load(imageUrl!!.replace("http:", "https:"))
        .into(imageView)

}
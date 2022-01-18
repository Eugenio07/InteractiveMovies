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
fun bindImage(imgView: ImageView, imageUrl: String?) {
    imageUrl?.let { image ->
        Logger.d(imageUrl)
        Picasso.get()
            .load(image)
            .into(imgView)
    }
}

//@BindingAdapter("movieListData")
//fun bindRecyclerViewProducts(recyclerView: RecyclerView, data: List<Movie>?) {
//    val adapter = recyclerView.adapter as AdapterItem
//    adapter.submitList(data)
//}
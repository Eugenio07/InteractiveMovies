package com.example.interactivemovies.ui.listings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Movie
import com.example.interactivemovies.databinding.MovieItemBinding
import com.example.interactivemovies.ui.listings.ListingsAdapter.MovieHolder



class ListingsAdapter(private val moviesList: List<Movie>, private val clickListener: ListingsListener):
RecyclerView.Adapter<MovieHolder>(){

    class MovieHolder(private val binding: MovieItemBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun render(item: Movie, clickListener: ListingsListener){
                with(binding){
                   // movieTitle.text = item.name
                    binding.movie = item.copy()
                    this.clickListener = clickListener
                    executePendingBindings()
//                    val imageURL = "$route${item.media[0].resource}"
//                    Logger.d(imageURL)
//                    Picasso.get()
//                        .load(imageURL)
//                        .into(moviePoster)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(layoutInflater, parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.render(moviesList[position], clickListener)
    }

    override fun getItemCount(): Int = moviesList.size

}

class ListingsListener(val clickListener: (movie: Movie) -> Unit){
    fun onClick(movie: Movie) = clickListener(movie)
}
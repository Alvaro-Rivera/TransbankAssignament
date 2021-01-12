package cl.alvaro.transbankapp.movielist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import cl.alvaro.transbankapp.databinding.CardMovieListItemBinding

class MovieListViewHolder(binding:CardMovieListItemBinding): RecyclerView.ViewHolder(binding.root) {
    val container:ConstraintLayout = binding.movieContainer
    val poster:ImageView = binding.moviePoster
    val title: TextView = binding.movieTitle
    val overview: TextView = binding.movieOverview
}
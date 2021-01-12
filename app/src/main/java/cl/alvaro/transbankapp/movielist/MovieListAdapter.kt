package cl.alvaro.transbankapp.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.alvaro.transbankapp.R
import cl.alvaro.transbankapp.databinding.CardMovieListItemBinding
import cl.alvaro.transbankapp.models.ui.UIMovieResult
import com.bumptech.glide.Glide

class MovieListAdapter (private val listData: MutableList<UIMovieResult>,
                        private var listener:MovieClickListener?)
    :RecyclerView.Adapter<MovieListViewHolder> () {

    fun updateListData(newList: List<UIMovieResult>) {
        listData.clear()
        listData.addAll(newList)
        notifyDataSetChanged()
    }

    fun removeListener() {
        listener = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val binding = CardMovieListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val data = listData[position]
        holder.container.setOnClickListener { listener?.onItemClick(data) }
        holder.title.text = data.movieName
        holder.overview.text = data.movieOverview
        if (data.moviePoster.isNotBlank()) {
            Glide.with(holder.itemView)
                .load(data.moviePoster)
                .placeholder(R.drawable.ic_movie_open_outline)
                .into(holder.poster)
        } else {
            Glide.with(holder.itemView)
                .load(R.drawable.ic_movie_open_outline)
                .into(holder.poster)
        }
    }

    override fun getItemCount(): Int = listData.size
}
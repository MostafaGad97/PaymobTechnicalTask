package com.example.paymobtechnicaltask.ui.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paymobtechnicaltask.databinding.LayoutMovieBinding
import com.example.paymobtechnicaltask.domain.model.Movie
import com.example.paymobtechnicaltask.ui.utils.getMoviePosterUrl
import com.example.paymobtechnicaltask.ui.utils.loadImageFromUrl

class MoviesAdapter(
    private val movieClickListener: MovieClickListener
) : PagingDataAdapter<Movie, MoviesAdapter.MovieViewHolder>(MovieComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = LayoutMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let {
            holder.bind(it)
        }
    }

    inner class MovieViewHolder(private val binding: LayoutMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                ivPoster.loadImageFromUrl(getMoviePosterUrl(movie.posterPath))
                tvName.text = movie.title
                tvRate.text = movie.voteAverage.toString()
                tvRealiseDate.text = movie.releaseDate
                btnFavorite.isSelected = movie.isFavorite

                btnFavorite.setOnClickListener {
                    btnFavorite.isSelected = !btnFavorite.isSelected
                    movie.isFavorite = btnFavorite.isSelected
                    movieClickListener.onFavoriteClick(movie)
                }

                itemView.setOnClickListener {
                    movieClickListener.onMovieClick(movie)
                }
            }
        }
    }

    object MovieComparator : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}

interface MovieClickListener {
    fun onMovieClick(movie: Movie)
    fun onFavoriteClick(movie: Movie)
}
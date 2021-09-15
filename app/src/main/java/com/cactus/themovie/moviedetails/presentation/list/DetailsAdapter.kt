package com.cactus.themovie.moviedetails.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cactus.themovie.databinding.ItemSimilarMovieBinding
import com.cactus.themovie.moviedetails.domain.SimilarMovie

class DetailsAdapter() : RecyclerView.Adapter<DetailsViewHolder>() {

    var list: List<SimilarMovie> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSimilarMovieBinding.inflate(layoutInflater, parent, false)
        val viewModel = ItemSimilarViewModel()
        binding.viewmodel = viewModel
        return DetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        val movie = list[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = list.size
}

class DetailsViewHolder(private val binding: ItemSimilarMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: SimilarMovie) {
        binding.viewmodel?.apply {
            title.set(movie.title ?: "")
            posterUrl.set(movie.posterUrl ?: "")
            genres.set(movie.genres ?: "")
            setReleaseYear(movie.releaseDate ?: "0000")
        }
    }
}
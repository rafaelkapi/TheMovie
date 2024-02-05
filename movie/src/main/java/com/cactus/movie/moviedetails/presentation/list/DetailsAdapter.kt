package com.cactus.movie.moviedetails.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cactus.movie.databinding.ItemSimilarMovieBinding
import com.cactus.movie.moviedetails.presentation.model.SimilarMoviesVo
import com.squareup.picasso.Picasso

class DetailsAdapter() : RecyclerView.Adapter<DetailsViewHolder>() {

//    var list: List<SimilarMoviesVo> = emptyList()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }

    private val differ: AsyncListDiffer<SimilarMoviesVo?> = AsyncListDiffer(this, DIFF_CALLBACK)

    fun setViewItems(list: List<SimilarMoviesVo>) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSimilarMovieBinding.inflate(layoutInflater, parent, false)
        return DetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        differ.currentList[position]?.let {
            holder.bind(it)
        }

    }

    override fun getItemCount(): Int = differ.currentList.size

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SimilarMoviesVo>() {
            override fun areItemsTheSame(
                oldItem: SimilarMoviesVo,
                newItem: SimilarMoviesVo
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: SimilarMoviesVo,
                newItem: SimilarMoviesVo
            ): Boolean {
                return oldItem.title == newItem.title &&
                        oldItem.posterUrl == newItem.posterUrl
            }
        }
    }
}

class DetailsViewHolder(private val view: ItemSimilarMovieBinding) :
    RecyclerView.ViewHolder(view.root) {

    fun bind(movie: SimilarMoviesVo) {

        with(view) {
            Picasso.get().load(movie.posterUrl).into(view.poster)
            title.text = movie.title
            year.text = movie.releaseDate
            genres.text = movie.movieGenres
        }

    }
}
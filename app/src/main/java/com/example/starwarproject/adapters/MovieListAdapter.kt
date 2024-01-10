package com.example.starwarproject.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarproject.databinding.ItemMovieDetailBinding
import com.example.starwarproject.model.CharacterMoviesResponse
import com.example.starwarproject.utils.getProgressDrawable


@SuppressLint("NotifyDataSetChanged")
class MovieListAdapter(
    private var movies: ArrayList<CharacterMoviesResponse>,
) :
    RecyclerView.Adapter<MovieListAdapter.CountryViewHolder>() {


    fun updateMovies(newMovies: List<CharacterMoviesResponse>) {
        movies.clear()
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieDetailBinding.inflate(inflater, parent, false)
        return CountryViewHolder(binding)
    }


    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(movies[position])

    }

    fun filterList(filterList: ArrayList<CharacterMoviesResponse>) {
        movies = filterList
        notifyDataSetChanged()
    }

    class CountryViewHolder(private val binding: ItemMovieDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val progressDrawable = getProgressDrawable(binding.root.context)

        fun bind(movie: CharacterMoviesResponse) {
//            binding.item = movie
//            binding.root.setOnClickListener {
//            }

       binding.titleTextView.text= movie.title
        }
    }
}

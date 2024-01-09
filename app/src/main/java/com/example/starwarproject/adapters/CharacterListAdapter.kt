package com.example.starwarproject.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarproject.databinding.ItemDisplayBinding
import com.example.starwarproject.model.ResultsItem
import com.example.starwarproject.utils.getProgressDrawable


@SuppressLint("NotifyDataSetChanged")
class CharacterListAdapter(
    private var characterItems: ArrayList<ResultsItem>,
    private val onClickListener: OnClickListener
) :
    RecyclerView.Adapter<CharacterListAdapter.CharacterViewHolder>() {


    fun updateMovies(newCharacters: List<ResultsItem>) {
        characterItems.clear()
        characterItems.addAll(newCharacters)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDisplayBinding.inflate(inflater, parent, false)
        return CharacterViewHolder(binding)
    }


    override fun getItemCount() = characterItems.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characterItems[position])
        holder.itemView.setOnClickListener {
            onClickListener.onClick(characterItems[position])
        }

    }

    fun filterList(filterList: ArrayList<ResultsItem>) {
        characterItems = filterList
        notifyDataSetChanged()
    }

    class CharacterViewHolder(private val binding: ItemDisplayBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val progressDrawable = getProgressDrawable(binding.root.context)

        fun bind(characters: ResultsItem) {
            binding.item = characters
            binding.root.setOnClickListener {
            }
//            binding.itemImage
//                .loadImage("https://image.tmdb.org/t/p/w500/"+movie.posterPath, progressDrawable)
        }
    }
}

class OnClickListener(val clickListener: (characters: ResultsItem) -> Unit) {
    fun onClick(country: ResultsItem) = clickListener(country)
}
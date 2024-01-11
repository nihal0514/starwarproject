package com.example.starwarproject.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarproject.databinding.ItemDisplayBinding
import com.example.starwarproject.model.ResultsItem
import com.example.starwarproject.utils.getProgressDrawable
import com.example.starwarproject.utils.loadImage


@SuppressLint("NotifyDataSetChanged")
class CharacterListAdapter(
    private val onClickListener: OnClickListener
) :PagingDataAdapter<ResultsItem, CharacterListAdapter.CharacterViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDisplayBinding.inflate(inflater, parent, false)
        return CharacterViewHolder(binding,onClickListener)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {

        val character = getItem(position)
        character?.let {
            holder.bind(it)
        }

    }

 /*   fun filterList(filterList: ArrayList<ResultsItem>) {
        characterItems = filterList
        notifyDataSetChanged()
    }
*/
    class CharacterViewHolder(private val binding: ItemDisplayBinding, private val listener: OnClickListener) :
        RecyclerView.ViewHolder(binding.root) {

        private val progressDrawable = getProgressDrawable(binding.root.context)

        fun bind(characters: ResultsItem) {
            binding.item = characters
            binding.itemTitle.text= characters.name
            itemView.setOnClickListener {
                listener.onClick(characters)
            }
            binding.itemImage
                .loadImage("https://starwars-visualguide.com/assets/img/characters/${position + 2}.jpg", progressDrawable)

        }
    }
    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ResultsItem>() {
            override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}

interface OnClickListener {
    fun onClick(country: ResultsItem)
}
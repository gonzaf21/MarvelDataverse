package com.naisuapps.marveldataverse.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.naisuapps.marveldataverse.R
import com.naisuapps.marveldataverse.data.model.characters.Character

class CharactersAdapter :
    PagingDataAdapter<Character, CharactersAdapter.ViewHolder>(CHARACTER_COMPARATOR) {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvCharacterItem: TextView = view.findViewById(R.id.tv_character_item)
        var ivCharacterItem: ImageView = view.findViewById(R.id.iv_character_item)

        init {
            // Define click listener for the ViewHolder's View.
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.ivCharacterItem.load(item?.thumbnail.toString()) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }
        holder.tvCharacterItem.text = item?.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_character_item, parent, false)

        return ViewHolder(view)
    }

    companion object {
        private val CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem == newItem
        }
    }
}
package com.naisuapps.marveldataverse.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.metadata
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.naisuapps.marveldataverse.R
import com.naisuapps.marveldataverse.data.model.comics.Comic

class ComicsAdapter(private var comicsList: List<Comic>) :
    RecyclerView.Adapter<ComicsAdapter.ViewHolder>() {
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvComicItem: TextView = view.findViewById(R.id.tv_comic_item)
        var ivComicItem: ImageView = view.findViewById(R.id.iv_comic_item)

        init {
            // Define click listener for the ViewHolder's View.
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_comic_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val currentItem = comicsList[position]
        // Get element from your comicsList at this position and replace the
        // contents of the view with that element
        viewHolder.tvComicItem.text = currentItem.title
        viewHolder.ivComicItem.load(currentItem.thumbnail.toString()) {
            crossfade(true)
            transformations(RoundedCornersTransformation(20f))
        }
    }

    // Return the size of your comicsList (invoked by the layout manager)
    override fun getItemCount() = comicsList.size

    /**
     * Updates list after comparing them with diffUtils.
     */
    fun updateComicsList(newComicsList: List<Comic>) {
        val diffUtil = ComicsDiffCallback(newComicsList, this.comicsList)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        this.comicsList = newComicsList
        diffUtilResult.dispatchUpdatesTo(this)
    }
}

/**
 * Comics diff utils callback.
 */
class ComicsDiffCallback(
    private var newComicsList: List<Comic>,
    private var oldComicsList: List<Comic>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldComicsList.size
    }

    override fun getNewListSize(): Int {
        return newComicsList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldComicsList[oldItemPosition].id == newComicsList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldComicsList[oldItemPosition] == newComicsList[newItemPosition]
    }

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}
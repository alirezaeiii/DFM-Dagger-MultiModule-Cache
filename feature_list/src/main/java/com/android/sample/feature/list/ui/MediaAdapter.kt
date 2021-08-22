package com.android.sample.feature.list.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.sample.common.base.BaseAdapter
import com.android.sample.common.extension.layoutInflater
import com.android.sample.core.response.Media
import com.android.sample.feature.list.databinding.MediaItemBinding

class MediaAdapter(
    private val callback: OnClickListener
) : BaseAdapter<Media, MediaAdapter.MainViewHolder>(DiffCallback) {

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainViewHolder.from(parent)

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position), callback)
    }

    /**
     * ViewHolder for link items. All work is done by data binding.
     */
    class MainViewHolder(private val binding: MediaItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(media: Media, callback: OnClickListener) {
            binding.media = media
            binding.callback = callback
        }

        companion object {
            fun from(parent: ViewGroup): MainViewHolder {
                val binding = MediaItemBinding.inflate(
                    parent.context.layoutInflater,
                    parent,
                    false
                )
                return MainViewHolder(binding)
            }
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [Media]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Media>() {
        override fun areItemsTheSame(
            oldItem: Media, newItem: Media
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Media, newItem: Media
        ): Boolean = oldItem == newItem
    }

    /**
     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [Media]
     * associated with the current item to the [onClick] function.
     * @param clickListener lambda that will be called with the current [Media]
     */
    class OnClickListener(val clickListener: (link: Media) -> Unit) {
        fun onClick(link: Media) = clickListener(link)
    }
}
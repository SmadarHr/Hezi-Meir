package com.tap.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.tap.databinding.ItemVideoBinding
import com.tap.repository.entities.Video

class VideosAdapter(
    private var videosList: List<Video>,
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<VideosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemVideoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = videosList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val video = videosList[position]

        Glide.with(holder.itemView.context)
            .load(video.thumbnailUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerCrop()
            .into(holder.binding.videoImageView)

        holder.binding.titleTextView.text = video.title

        holder.binding.root.setOnClickListener {
            itemClickListener.onItemClick(video)
        }
    }

    fun update(videosList: List<Video>) {
        this.videosList = videosList
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface ItemClickListener {
        fun onItemClick(video: Video)
    }
}
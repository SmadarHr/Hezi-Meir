package com.tap.ui.movie

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.tap.databinding.FragmentVideoBinding
import com.tap.repository.entities.Video
import org.koin.androidx.viewmodel.ext.android.viewModel


class VideoFragment : Fragment() {

    private var _binding: FragmentVideoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: VideoViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initVideoViews()

        viewModel.actionLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is VideoViewModel.Action.ShowVideo -> watchYoutubeVideo(it.videoId)
            }
        }
    }

    private fun initVideoViews() {
        val video: Video? = arguments?.getParcelable(ARG_VIDEO)

        if (video != null) {
            binding.titleTextView.text = video.title

            Glide.with(this)
                .load(video.thumbnailUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(binding.videoImageView)

            binding.playTextView.setOnClickListener{
                viewModel.onPlayClicked(video)
            }
        }
    }

    fun watchYoutubeVideo(id: String) {
        val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$id"))
        val webIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("http://www.youtube.com/watch?v=$id")
        )
        try {
            startActivity(appIntent)
        } catch (ex: ActivityNotFoundException) {
            startActivity(webIntent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_VIDEO: String = "ARG_VIDEO"
    }
}
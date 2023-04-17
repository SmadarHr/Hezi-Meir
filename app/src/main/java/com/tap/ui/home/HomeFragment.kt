package com.tap.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tap.R
import com.tap.databinding.FragmentHomeBinding
import com.tap.repository.entities.Video
import com.tap.ui.movie.VideoFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), VideosAdapter.ItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModel()

    private val videosAdapter = VideosAdapter(emptyList(), this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initSearchView()
        initVideosViews()

        viewModel.actionLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is HomeViewModel.Action.ShowError -> showError(it.error)
                is HomeViewModel.Action.ShowVideoList -> updateVideoList(it.videosList)
                HomeViewModel.Action.StartLoading -> binding.progressBar.visibility = View.VISIBLE
            }
        }
    }

    private fun initVideosViews() {
        binding.videosRecyclerView.adapter = videosAdapter
        binding.videosRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun updateVideoList(videosList: List<Video>) {
        binding.progressBar.visibility = View.GONE
        videosAdapter.update(videosList)
    }

    private fun showError(error: String) {
        binding.progressBar.visibility = View.GONE
        Toast.makeText(
            context,
            error,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun initSearchView() {
        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.search(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }

        })
    }

    override fun onItemClick(video: Video) {
        val bundle = Bundle()
        bundle.putParcelable(VideoFragment.ARG_VIDEO, video)

        findNavController().navigate(
            R.id.action_homeFragment_to_movieFragment,
            bundle
        )

//        activity?.supportFragmentManager?.beginTransaction()
//            ?.replace(R.id.nav_host_fragment_activity_main, EmptyFragment())
//            ?.addToBackStack("name")
//            ?.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
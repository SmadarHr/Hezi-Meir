package com.tap.ui.empty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tap.databinding.FragmentEmptyBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EmptyFragment : Fragment() {

    private var _binding: FragmentEmptyBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EmptyViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmptyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
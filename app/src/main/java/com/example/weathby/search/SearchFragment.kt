package com.example.weathby.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.weathby.R
import com.example.weathby.databinding.FragmentSearchBinding

/**
 * A fragment representing a list of Items.
 */
class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val searchAdapter by lazy { SearchAdapter(
        SearchAdapter.OnClickListener { data ->

        }
    )}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() = binding.apply {
        searchList.apply {
            adapter = searchAdapter
            itemAnimator = null
        }

        backArrow.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_homeFragment2)
        }
    }
}
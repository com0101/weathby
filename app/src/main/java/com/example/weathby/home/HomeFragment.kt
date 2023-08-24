package com.example.weathby.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.weathby.MainViewModel
import com.example.weathby.R
import com.example.weathby.databinding.FragmentHomeBinding
import com.example.weathby.resource.Resource
import com.example.weathby.setProgressVisibility
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val cardAdapter by lazy { HomeCityAdapter(
        HomeCityAdapter.OnClickListener { data, extra ->
            // Safe Args
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(data)
            this.findNavController().navigate(action, extra)
        }
    )}
    private val tempAdapter by lazy { HomeTemAdapter() }
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupViewModel()
    }

    private fun setupView() = binding.apply {
        postponeEnterTransition()
        cityList.apply {
            adapter = cardAdapter
            itemAnimator = null
            doOnPreDraw { startPostponedEnterTransition() }
        }
        cityTemList.apply {
            adapter = tempAdapter
            itemAnimator = null
            doOnPreDraw { startPostponedEnterTransition() }
        }

        searchButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

        settingButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_settingFragment)
        }
    }

    private fun setupViewModel() {
        viewModel.cardList.observe(viewLifecycleOwner) {
            binding.cardProgress.setProgressVisibility {
                it is Resource.Loading
            }

            when(it) {
                is Resource.Success -> {
                    cardAdapter.submitList(it.data)
                }
                is Resource.Error -> {
                    Snackbar.make(binding.root, "${it.message}", Snackbar.LENGTH_LONG).show()
                }
                else -> {}
            }
        }
        viewModel.tempList.observe(viewLifecycleOwner) {
            binding.tempProgress.setProgressVisibility {
                it is Resource.Loading
            }
            when(it) {
                is Resource.Success -> {
                    tempAdapter.submitList(it.data)
                }
                is Resource.Error -> {
                    Snackbar.make(binding.root, "${it.message}", Snackbar.LENGTH_LONG).show()
                }
                else -> {}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.weathby.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.weathby.R
import com.example.weathby.databinding.FragmentHomeBinding
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
        HomeCityAdapter.OnClickListener { data ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(data)
            this.findNavController().navigate(action)
        }
    )}
    private val tempAdapter by lazy { HomeTempAdapter() }

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
    }

    private fun setupView() = binding.apply {
        val fakeData = CityCard(
            0,
            "LONDON",
            "MONDAY",
            "30°",
            "15m/s",
            "30%",
            "50%",
            false,
            listOf(
                CityDayTemp(
                    "Tue",
                    IconType.SUN,
                    "30°",
                    "25°"
                ), CityDayTemp(
                    "Wed",
                    IconType.SUN,
                    "30°",
                    "30°"
                ))
        )
        val mock = listOf(CityHourTemp(
            0, "12:00", "30°", IconType.CLOUD
        ), CityHourTemp(
            0, "13:00", "27°", IconType.SUN
        ),CityHourTemp(
            0, "14:00", "29°", IconType.RAIN
        ))
        cityList.apply {
            adapter = cardAdapter
            itemAnimator = null
            cardAdapter.submitList(listOf(fakeData))
        }
        cityTemList.apply {
            adapter = tempAdapter
            itemAnimator = null
        }
        tempAdapter.submitList(mock)
        searchButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

        settingButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_settingFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
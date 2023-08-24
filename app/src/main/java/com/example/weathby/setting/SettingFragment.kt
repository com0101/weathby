package com.example.weathby.setting

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.example.weathby.AppStored
import com.example.weathby.databinding.FragmentSettingBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch

private val Context.dataStore by preferencesDataStore("settings") // extension

class SettingFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    private var bottomSheetBehavior: BottomSheetBehavior<View>? = null
    private var bottomSheetCallback: BottomSheetBehavior.BottomSheetCallback? = null
    private val appStored by lazy { AppStored(requireContext().dataStore) } // appstore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSettingBinding.inflate(inflater, container, false)

        bottomSheetBehavior = BottomSheetBehavior.from(binding.content as View)
        bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                // Do something for new state.
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // Do something for slide offset.
            }
        }
        bottomSheetCallback?.let {
            bottomSheetBehavior?.addBottomSheetCallback(it)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.darkModeSwitch.apply {
            lifecycleScope.launch {
                appStored.darkModePreferencesFlow.collect {
                    isChecked = it
                }
            }
            setOnCheckedChangeListener { _, isChecked ->
                lifecycleScope.launch {
                    appStored.enableDarkMode(isChecked)
                }
            }
        }
    }


    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        bottomSheetCallback?.let {
            bottomSheetBehavior?.removeBottomSheetCallback(it)
        }
    }

}
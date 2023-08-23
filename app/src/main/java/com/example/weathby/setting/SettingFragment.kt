package com.example.weathby.setting

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weathby.databinding.FragmentSettingBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SettingFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    private var bottomSheetBehavior: BottomSheetBehavior<View>? = null
    private var bottomSheetCallback: BottomSheetBehavior.BottomSheetCallback? = null

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


    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        bottomSheetCallback?.let {
            bottomSheetBehavior?.removeBottomSheetCallback(it)
        }
    }

}
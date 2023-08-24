package com.example.weathby.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weathby.R
import com.example.weathby.databinding.ItemDayTempBinding
import com.example.weathby.home.CityDayTemp
import com.example.weathby.home.IconType

class DetailTemAdapter: ListAdapter<CityDayTemp, DetailTemAdapter.DayViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        return DayViewHolder(ItemDayTempBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.setupView(currentList[position])
    }

    inner class DayViewHolder(private val binding: ItemDayTempBinding): RecyclerView.ViewHolder(binding.root) {
        fun setupView(dayTemp: CityDayTemp) = binding.apply {
            cityDay.text = dayTemp.day
            cityWeatherIcon.setImageDrawable(ContextCompat.getDrawable(root.context, when(dayTemp.icon) {
                IconType.SUN -> R.drawable.sun_gray
                IconType.CLOUD -> R.drawable.cloud_gray
                IconType.RAIN -> R.drawable.rain_gray
            }))
            cityMaxTem.text = dayTemp.maxTemp
            cityMinTem.text = dayTemp.minTemp
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CityDayTemp>() {
        override fun areItemsTheSame(oldItem: CityDayTemp, newItem: CityDayTemp): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: CityDayTemp, newItem: CityDayTemp): Boolean {
            return oldItem.day == newItem.day
        }
    }
}
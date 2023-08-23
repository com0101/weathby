package com.example.weathby.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weathby.R
import com.example.weathby.databinding.ItemCityTemBinding

class HomeTempAdapter : ListAdapter<CityHourTemp, HomeTempAdapter.HourViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourViewHolder {
        return HourViewHolder(ItemCityTemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HourViewHolder, position: Int) {
        holder.setupView(currentList[position])
    }

    inner class HourViewHolder(private val binding: ItemCityTemBinding): RecyclerView.ViewHolder(binding.root) {
        fun setupView(hourTemp: CityHourTemp) = binding.apply {
            cityHour.text = hourTemp.time
            cityWeatherIcon.setImageDrawable(
                ContextCompat.getDrawable(root.context, when(hourTemp.iconType) {
                IconType.SUN -> R.drawable.sun
                IconType.CLOUD -> R.drawable.cloud
                IconType.RAIN -> R.drawable.rain
            }))
            cityWeatherTem.text = hourTemp.temp
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CityHourTemp>() {
        override fun areItemsTheSame(oldItem: CityHourTemp, newItem: CityHourTemp): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: CityHourTemp, newItem: CityHourTemp): Boolean {
            return oldItem.id == newItem.id
        }
    }

}
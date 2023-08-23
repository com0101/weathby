package com.example.weathby.search

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.weathby.databinding.ItemSearchCityBinding

/**
 * [RecyclerView.Adapter] that can display a [CityResult].
 */
class SearchAdapter(private val onClickListener: OnClickListener) : ListAdapter<CityResult, SearchAdapter.SearchViewHolder>(DiffCallback) {

    class OnClickListener(val clickListener: (cityName: String) -> Unit) {
        fun onClick(cityName: String) = clickListener(cityName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(ItemSearchCityBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.setupView(currentList[position])
    }

    inner class SearchViewHolder(private val binding: ItemSearchCityBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setupView(result: CityResult) = binding.apply {
            cityName.text = result.cityName
            country.text = result.country
            root.setOnClickListener {
                onClickListener.onClick(result.cityName)
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CityResult>() {
        override fun areItemsTheSame(oldItem: CityResult, newItem: CityResult): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: CityResult, newItem: CityResult): Boolean {
            return oldItem.id == newItem.id
        }
    }

}
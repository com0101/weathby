package com.example.weathby.search

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.weathby.R

import com.example.weathby.search.placeholder.PlaceholderContent.PlaceholderItem
import com.example.weathby.databinding.FragmentSearchBinding

/**
 * [RecyclerView.Adapter] that can display a [CitiesVO].
 * TODO: Replace the implementation with code for your data type.
 */
class SearchAdapter(
    private val values: List<CitiesVO>
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
//        holder.idView.text = item.id
//        holder.contentView.text = item.content
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}
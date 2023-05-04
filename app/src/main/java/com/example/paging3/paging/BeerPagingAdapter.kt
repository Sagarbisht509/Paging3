package com.example.paging3.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3.R
import com.example.paging3.models.Beer

class BeerPagingAdapter : PagingDataAdapter<Beer, BeerPagingAdapter.BeerViewHolder>(COMPARATOR) {
    class BeerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.name)
        val image = itemView.findViewById<ImageView>(R.id.image)
        val description = itemView.findViewById<TextView>(R.id.description)

    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Beer>() {
            override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {

            holder.name.text = item.name
            holder.description.text = item.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.beer_item, parent, false)
        return BeerViewHolder(view)
    }
}
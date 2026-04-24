package com.example.sportsnewsfeedapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsnewsfeedapp.R
import com.example.sportsnewsfeedapp.model.NewsItem

class FeaturedAdapter(
    private val items: List<NewsItem>,
    private val onClick: (NewsItem) -> Unit
) : RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder>() {

    inner class FeaturedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivFeatured: ImageView = itemView.findViewById(R.id.ivFeatured)
        val tvFeaturedTitle: TextView = itemView.findViewById(R.id.tvFeaturedTitle)
        val tvFeaturedCategory: TextView = itemView.findViewById(R.id.tvFeaturedCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_featured, parent, false)
        return FeaturedViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeaturedViewHolder, position: Int) {
        val item = items[position]
        holder.ivFeatured.setImageResource(item.imageResId)
        holder.tvFeaturedTitle.text = item.title
        holder.tvFeaturedCategory.text = item.category

        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }

    override fun getItemCount(): Int = items.size
}
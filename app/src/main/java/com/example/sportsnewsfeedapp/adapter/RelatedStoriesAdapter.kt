package com.example.sportsnewsfeedapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsnewsfeedapp.R
import com.example.sportsnewsfeedapp.model.NewsItem

class RelatedStoriesAdapter(
    private val items: List<NewsItem>,
    private val onClick: (NewsItem) -> Unit
) : RecyclerView.Adapter<RelatedStoriesAdapter.RelatedStoriesViewHolder>() {

    inner class RelatedStoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvRelatedTitle: TextView = itemView.findViewById(R.id.tvRelatedTitle)
        val tvRelatedCategory: TextView = itemView.findViewById(R.id.tvRelatedCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelatedStoriesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_related, parent, false)
        return RelatedStoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: RelatedStoriesViewHolder, position: Int) {
        val item = items[position]
        holder.tvRelatedTitle.text = item.title
        holder.tvRelatedCategory.text = item.category

        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }

    override fun getItemCount(): Int = items.size
}
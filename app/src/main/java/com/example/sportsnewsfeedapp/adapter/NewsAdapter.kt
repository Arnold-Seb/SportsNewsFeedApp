package com.example.sportsnewsfeedapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsnewsfeedapp.R
import com.example.sportsnewsfeedapp.model.NewsItem

class NewsAdapter(
    private var items: List<NewsItem>,
    private val onClick: (NewsItem) -> Unit
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivNews: ImageView = itemView.findViewById(R.id.ivNews)
        val tvNewsTitle: TextView = itemView.findViewById(R.id.tvNewsTitle)
        val tvNewsCategory: TextView = itemView.findViewById(R.id.tvNewsCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = items[position]
        holder.ivNews.setImageResource(item.imageResId)
        holder.tvNewsTitle.text = item.title
        holder.tvNewsCategory.text = item.category

        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateData(newItems: List<NewsItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}
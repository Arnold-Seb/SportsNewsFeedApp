package com.example.sportsnewsfeedapp.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsnewsfeedapp.R
import com.example.sportsnewsfeedapp.adapter.FeaturedAdapter
import com.example.sportsnewsfeedapp.adapter.NewsAdapter
import com.example.sportsnewsfeedapp.data.NewsRepository
import com.example.sportsnewsfeedapp.model.NewsItem

class HomeFragment : Fragment() {

    interface HomeNavigationListener {
        fun openDetail(newsItem: NewsItem)
        fun openBookmarks()
    }

    private var listener: HomeNavigationListener? = null
    private lateinit var newsAdapter: NewsAdapter
    private var allLatestNews: List<NewsItem> = emptyList()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? HomeNavigationListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val rvFeatured = view.findViewById<RecyclerView>(R.id.rvFeatured)
        val rvLatestNews = view.findViewById<RecyclerView>(R.id.rvLatestNews)
        val searchView = view.findViewById<SearchView>(R.id.searchView)
        val btnBookmarks = view.findViewById<Button>(R.id.btnBookmarks)

        val featuredItems = NewsRepository.getFeaturedMatches()
        allLatestNews = NewsRepository.getLatestNews()

        rvFeatured.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvFeatured.adapter = FeaturedAdapter(featuredItems) { selectedItem ->
            listener?.openDetail(selectedItem)
        }

        newsAdapter = NewsAdapter(allLatestNews) { selectedItem ->
            listener?.openDetail(selectedItem)
        }

        rvLatestNews.layoutManager = LinearLayoutManager(requireContext())
        rvLatestNews.adapter = newsAdapter

        btnBookmarks.setOnClickListener {
            listener?.openBookmarks()
        }

        searchView.queryHint = "Filter by sport category"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterNews(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterNews(newText)
                return true
            }
        })
    }

    private fun filterNews(query: String?) {
        val input = query?.trim()?.lowercase().orEmpty()

        if (input.isEmpty()) {
            newsAdapter.updateData(allLatestNews)
            return
        }

        val filtered = allLatestNews.filter {
            it.category.lowercase().contains(input) || it.title.lowercase().contains(input)
        }

        newsAdapter.updateData(filtered)
    }
}
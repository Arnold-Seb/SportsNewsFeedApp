package com.example.sportsnewsfeedapp.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsnewsfeedapp.R
import com.example.sportsnewsfeedapp.adapter.RelatedStoriesAdapter
import com.example.sportsnewsfeedapp.data.NewsRepository
import com.example.sportsnewsfeedapp.model.NewsItem
import com.example.sportsnewsfeedapp.utils.BookmarkManager

class DetailFragment : Fragment() {

    interface DetailNavigationListener {
        fun goBack()
    }

    private var listener: DetailNavigationListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? DetailNavigationListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val item = arguments?.getSerializable(ARG_NEWS_ITEM) as? NewsItem ?: return

        val ivDetailImage = view.findViewById<ImageView>(R.id.ivDetailImage)
        val tvDetailTitle = view.findViewById<TextView>(R.id.tvDetailTitle)
        val tvDetailDescription = view.findViewById<TextView>(R.id.tvDetailDescription)
        val rvRelatedStories = view.findViewById<RecyclerView>(R.id.rvRelatedStories)
        val btnBookmark = view.findViewById<Button>(R.id.btnBookmark)
        val btnBack = view.findViewById<Button>(R.id.btnBack)

        ivDetailImage.setImageResource(item.imageResId)
        tvDetailTitle.text = item.title
        tvDetailDescription.text = item.description

        val relatedStories = NewsRepository.getRelatedStories(item)

        rvRelatedStories.layoutManager = LinearLayoutManager(requireContext())
        rvRelatedStories.adapter = RelatedStoriesAdapter(relatedStories) { selectedItem ->
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, newInstance(selectedItem))
                .addToBackStack(null)
                .commit()
        }

        btnBookmark.setOnClickListener {
            BookmarkManager.saveBookmark(requireContext(), item)
            Toast.makeText(requireContext(), "Story bookmarked", Toast.LENGTH_SHORT).show()
        }

        btnBack.setOnClickListener {
            listener?.goBack()
        }
    }

    companion object {
        private const val ARG_NEWS_ITEM = "arg_news_item"

        fun newInstance(newsItem: NewsItem): DetailFragment {
            val fragment = DetailFragment()
            val bundle = Bundle()
            bundle.putSerializable(ARG_NEWS_ITEM, newsItem)
            fragment.arguments = bundle
            return fragment
        }
    }
}
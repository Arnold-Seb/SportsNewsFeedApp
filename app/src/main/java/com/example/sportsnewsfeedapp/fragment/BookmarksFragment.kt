package com.example.sportsnewsfeedapp.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsnewsfeedapp.R
import com.example.sportsnewsfeedapp.adapter.NewsAdapter
import com.example.sportsnewsfeedapp.utils.BookmarkManager

class BookmarksFragment : Fragment() {

    interface BookmarksNavigationListener {
        fun goBack()
    }

    private var listener: BookmarksNavigationListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? BookmarksNavigationListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_bookmarks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val rvBookmarks = view.findViewById<RecyclerView>(R.id.rvBookmarks)
        val tvEmptyBookmarks = view.findViewById<TextView>(R.id.tvEmptyBookmarks)
        val btnBackBookmarks = view.findViewById<Button>(R.id.btnBackBookmarks)

        val bookmarks = BookmarkManager.getBookmarks(requireContext())

        if (bookmarks.isEmpty()) {
            tvEmptyBookmarks.visibility = View.VISIBLE
            rvBookmarks.visibility = View.GONE
        } else {
            tvEmptyBookmarks.visibility = View.GONE
            rvBookmarks.visibility = View.VISIBLE

            rvBookmarks.layoutManager = LinearLayoutManager(requireContext())
            rvBookmarks.adapter = NewsAdapter(bookmarks) { selectedItem ->
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, DetailFragment.newInstance(selectedItem))
                    .addToBackStack(null)
                    .commit()
            }
        }

        btnBackBookmarks.setOnClickListener {
            listener?.goBack()
        }
    }
}
package com.example.sportsnewsfeedapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.sportsnewsfeedapp.fragment.BookmarksFragment
import com.example.sportsnewsfeedapp.fragment.DetailFragment
import com.example.sportsnewsfeedapp.fragment.HomeFragment
import com.example.sportsnewsfeedapp.model.NewsItem

class MainActivity : AppCompatActivity(),
    HomeFragment.HomeNavigationListener,
    DetailFragment.DetailNavigationListener,
    BookmarksFragment.BookmarksNavigationListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment(), false)
        }
    }

    private fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }

    override fun openDetail(newsItem: NewsItem) {
        replaceFragment(DetailFragment.newInstance(newsItem), true)
    }

    override fun openBookmarks() {
        replaceFragment(BookmarksFragment(), true)
    }

    override fun goBack() {
        supportFragmentManager.popBackStack()
    }
}
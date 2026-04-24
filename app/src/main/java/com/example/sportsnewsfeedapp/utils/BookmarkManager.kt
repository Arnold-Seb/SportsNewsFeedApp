package com.example.sportsnewsfeedapp.utils

import android.content.Context
import com.example.sportsnewsfeedapp.data.NewsRepository
import com.example.sportsnewsfeedapp.model.NewsItem

object BookmarkManager {

    private const val PREF_NAME = "bookmark_pref"
    private const val KEY_BOOKMARK_IDS = "bookmark_ids"

    fun saveBookmark(context: Context, newsItem: NewsItem) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val currentIds = prefs.getStringSet(KEY_BOOKMARK_IDS, mutableSetOf())?.toMutableSet()
            ?: mutableSetOf()

        currentIds.add(newsItem.id.toString())
        prefs.edit().putStringSet(KEY_BOOKMARK_IDS, currentIds).apply()
    }

    fun getBookmarks(context: Context): List<NewsItem> {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val savedIds = prefs.getStringSet(KEY_BOOKMARK_IDS, emptySet()) ?: emptySet()

        return savedIds.mapNotNull { id ->
            NewsRepository.getById(id.toInt())
        }
    }
}
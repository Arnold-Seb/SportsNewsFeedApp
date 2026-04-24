package com.example.sportsnewsfeedapp.model

import java.io.Serializable

data class NewsItem(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val imageResId: Int,
    val relatedIds: List<Int> = emptyList()
) : Serializable
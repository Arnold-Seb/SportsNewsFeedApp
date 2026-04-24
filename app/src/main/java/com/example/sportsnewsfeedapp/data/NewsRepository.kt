package com.example.sportsnewsfeedapp.data

import android.R
import com.example.sportsnewsfeedapp.model.NewsItem

object NewsRepository {

    private val allNews = listOf(
        NewsItem(
            id = 1,
            title = "Football Final Ends in Dramatic Win",
            description = "The football final ended with a last-minute goal in a dramatic finish. Fans celebrated as the underdogs claimed victory in the final minutes of the match.",
            category = "Football",
            imageResId = R.drawable.ic_menu_gallery,
            relatedIds = listOf(2, 4)
        ),
        NewsItem(
            id = 2,
            title = "Basketball Team Extends Winning Streak",
            description = "The home basketball team secured another strong win and extended their winning streak with an impressive team performance.",
            category = "Basketball",
            imageResId = R.drawable.ic_menu_camera,
            relatedIds = listOf(1, 6)
        ),
        NewsItem(
            id = 3,
            title = "Cricket Captain Scores Century",
            description = "The captain led the innings with a brilliant century, helping the team gain control of the match in style.",
            category = "Cricket",
            imageResId = R.drawable.ic_menu_report_image,
            relatedIds = listOf(5, 1)
        ),
        NewsItem(
            id = 4,
            title = "Football Coach Announces New Strategy",
            description = "A new tactical setup has been announced by the football coach ahead of the next round of fixtures.",
            category = "Football",
            imageResId = R.drawable.ic_menu_gallery,
            relatedIds = listOf(1, 3)
        ),
        NewsItem(
            id = 5,
            title = "Cricket Series Begins This Weekend",
            description = "Fans are preparing for a high-energy cricket series beginning this weekend, with strong squads on both sides.",
            category = "Cricket",
            imageResId = R.drawable.ic_menu_camera,
            relatedIds = listOf(3, 2)
        ),
        NewsItem(
            id = 6,
            title = "Basketball Rookie Impresses in Debut",
            description = "The rookie delivered a confident debut performance and immediately made an impact for the basketball side.",
            category = "Basketball",
            imageResId = R.drawable.ic_menu_report_image,
            relatedIds = listOf(2, 5)
        )
    )

    fun getFeaturedMatches(): List<NewsItem> {
        return listOf(allNews[0], allNews[1], allNews[2])
    }

    fun getLatestNews(): List<NewsItem> {
        return allNews
    }

    fun getById(id: Int): NewsItem? {
        return allNews.find { it.id == id }
    }

    fun getRelatedStories(newsItem: NewsItem): List<NewsItem> {
        return allNews.filter { it.id in newsItem.relatedIds }
    }
}
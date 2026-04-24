# Sports News Feed App

##  Overview
The Sports News Feed App is an Android application that displays sports news articles using a clean and structured user interface. It allows users to browse featured matches, view detailed news articles, filter content, and bookmark stories for later viewing.

##  Features
-  Single-activity architecture using Fragments
-  Horizontal RecyclerView for Featured Matches
-  Vertical RecyclerView for Latest Sports News
-  Search and filter functionality by category or title
-  Detailed news view with image, title, and description
-  Related stories displayed for each news item
-  Bookmark feature using local storage (SharedPreferences)
-  Bookmarks screen to view saved stories

## Technologies Used
- Kotlin
- Android Studio
- RecyclerView
- Fragments
- SharedPreferences (for bookmarking)

## Project Structure
- `MainActivity` – Handles navigation between fragments
- `HomeFragment` – Displays featured and latest news
- `DetailFragment` – Shows full news details
- `BookmarksFragment` – Displays saved news
- `NewsRepository` – Stores dummy data
- `NewsItem` – Data model
- Adapters – Handle RecyclerView data binding

## How to Run
1. Clone the repository
2. Open in Android Studio
3. Sync Gradle
4. Run the app on emulator/device

##  Author
Arnold Sebastian  
Student ID: S225095328

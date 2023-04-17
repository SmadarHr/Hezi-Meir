package com.tap.repository

interface YoutubeRepository {
    suspend fun getVideos(query: String) : YoutubeResponse
}
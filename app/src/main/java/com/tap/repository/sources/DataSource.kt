package com.tap.repository.sources

interface DataSource {
    suspend fun search(query: String): YouTubeVideosDTOResponse
}
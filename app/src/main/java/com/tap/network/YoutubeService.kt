package com.tap.network

import com.tap.network.entities.YouTubeDTO
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "AIzaSyC-av2LYtLWR-B3EP2rQVLGdhxPtbUwE8A"
interface YoutubeService {

    @GET("/youtube/v3/search")
    suspend fun search(
        @Query("part") part: String = "snippet",
        @Query("maxResults") maxResults: Int = 20,
        @Query("type") type: String = "video",
        @Query("key") key: String = API_KEY,
        @Query("q") query: String
    ): YouTubeDTO
}

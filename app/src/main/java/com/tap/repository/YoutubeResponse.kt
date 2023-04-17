package com.tap.repository

import com.tap.repository.entities.Video

sealed class YoutubeResponse {
    data class Success(val videosList: List<Video>) : YoutubeResponse()
    data class Failed(val errorMessage: String) : YoutubeResponse()
}
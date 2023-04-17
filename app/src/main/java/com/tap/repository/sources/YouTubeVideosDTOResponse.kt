package com.tap.repository.sources

import com.tap.network.entities.YouTubeDTO

sealed class YouTubeVideosDTOResponse {
    data class Success(val youTubeDTO: YouTubeDTO) : YouTubeVideosDTOResponse()
    data class Failed(val error: String) : YouTubeVideosDTOResponse()
}
package com.tap.repository.useCases

import com.tap.repository.YoutubeRepository
import com.tap.repository.YoutubeResponse

class SearchUseCase(
    private val youtubeRepository: YoutubeRepository
) {

    suspend operator fun invoke(query: String): YoutubeResponse {
        return youtubeRepository.getVideos(query)
    }
}
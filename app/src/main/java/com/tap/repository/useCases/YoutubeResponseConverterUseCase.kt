package com.tap.repository.useCases

import com.tap.network.entities.YouTubeDTO
import com.tap.repository.YoutubeResponse
import com.tap.repository.entities.Video
import com.tap.repository.sources.YouTubeVideosDTOResponse

class YoutubeResponseConverterUseCase {

    operator fun invoke(youTubeVideosDTOResponse: YouTubeVideosDTOResponse): YoutubeResponse {
        return when (youTubeVideosDTOResponse) {
            is YouTubeVideosDTOResponse.Failed -> YoutubeResponse.Failed("Something went wrong, please try again later")
            is YouTubeVideosDTOResponse.Success -> convert(youTubeVideosDTOResponse.youTubeDTO)
        }
    }

    private fun convert(youTubeDTO: YouTubeDTO): YoutubeResponse {
        if (youTubeDTO.items == null) {
            return YoutubeResponse.Failed("items is missing")
        }

        val videosList = mutableListOf<Video>()

        for (item in youTubeDTO.items) {
            if (item.snippet == null) {
                continue
            }

            if (item.snippet.title == null) {
                continue
            }

            if (item.snippet.thumbnails == null) {
                continue
            }

            if (item.snippet.thumbnails.high == null) {
                continue
            }

            if (item.snippet.thumbnails.high.url == null) {
                continue
            }

            if (item.id == null) {
                continue
            }

            if (item.id.videoId == null) {
                continue
            }

            videosList.add(
                Video(
                    item.snippet.title,
                    item.snippet.thumbnails.high.url,
                    item.id.videoId
                )
            )
        }

        return YoutubeResponse.Success(videosList)
    }
}
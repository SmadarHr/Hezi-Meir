package com.tap.repository.sources

import com.tap.network.YoutubeService

// I didn't care the error results, of course we should add adapter for the retrofit layer for different response, I used generic exception error
class RemoteDataSource(
    private val youtubeService: YoutubeService
) : DataSource {

    override suspend fun search(query: String): YouTubeVideosDTOResponse {
        return try {
            val namePredictionDTO = youtubeService.search(query = query)
            YouTubeVideosDTOResponse.Success(namePredictionDTO)
        } catch (e: Exception) {
            YouTubeVideosDTOResponse.Failed(e.message ?: "Something went wrong")
        }
    }
}
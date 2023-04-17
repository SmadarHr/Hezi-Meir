package com.tap.repository

import com.tap.repository.sources.DataSource
import com.tap.repository.sources.YouTubeVideosDTOResponse
import com.tap.repository.useCases.YoutubeResponseConverterUseCase

//Here we should add cacheDataSource and on getVideos we should add waterfall with remote and cache layers - the cache will use DB by room
class TapYoutubeRepository(
    private val remoteDataSource: DataSource,
    private val youtubeResponseConverterUseCase: YoutubeResponseConverterUseCase
): YoutubeRepository {

    override suspend fun getVideos(query: String): YoutubeResponse {
        return when (val youTubeVideosDTOResponse = remoteDataSource.search(query)) {
            is YouTubeVideosDTOResponse.Failed -> YoutubeResponse.Failed("Something went wrong, please try again later")
            is YouTubeVideosDTOResponse.Success -> youtubeResponseConverterUseCase(youTubeVideosDTOResponse)
        }
    }
}
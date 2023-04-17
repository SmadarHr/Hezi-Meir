package com.tap.repository.di

import com.tap.repository.TapYoutubeRepository
import com.tap.repository.YoutubeRepository
import com.tap.repository.sources.DataSource
import com.tap.repository.sources.RemoteDataSource
import com.tap.repository.useCases.SearchUseCase
import com.tap.repository.useCases.YoutubeResponseConverterUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val REMOTE_DATA_SOURCE = "REMOTE_DATA_SOURCE"

val repositoryModule = module {

    factory<DataSource>(named(REMOTE_DATA_SOURCE)) {
        RemoteDataSource(youtubeService = get())
    }

    factory {
        YoutubeResponseConverterUseCase()
    }

    single<YoutubeRepository> {
        TapYoutubeRepository(
            remoteDataSource = get(named(REMOTE_DATA_SOURCE)),
            youtubeResponseConverterUseCase = get()
        )
    }

    factory { SearchUseCase(youtubeRepository = get()) }
}
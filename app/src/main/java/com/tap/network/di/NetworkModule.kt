package com.tap.network.di

import com.google.gson.Gson
import com.tap.network.YoutubeService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "BASE_URL"
private const val BASE_SERVER_URL = "https://www.googleapis.com/"

val networkModule = module {

    factory(named(name = BASE_URL)) { BASE_SERVER_URL }

    single<YoutubeService> {
        get<Retrofit>().create(YoutubeService::class.java)
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            })
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named(name = BASE_URL)))
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }

    single { Gson() }
}
package com.tap.repository.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Video(
    val title: String,
    val thumbnailUrl: String,
    val videoId: String
) : Parcelable


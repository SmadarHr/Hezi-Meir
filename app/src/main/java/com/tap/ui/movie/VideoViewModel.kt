package com.tap.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tap.repository.entities.Video

class VideoViewModel : ViewModel() {

    private val actionMutableLiveData = MutableLiveData<Action>()
    val actionLiveData: LiveData<Action>
        get() = actionMutableLiveData

    fun onPlayClicked(video: Video) {
        actionMutableLiveData.value = Action.ShowVideo(video.videoId)
    }

    sealed class Action {
        data class ShowVideo(val videoId: String) : Action()
    }
}
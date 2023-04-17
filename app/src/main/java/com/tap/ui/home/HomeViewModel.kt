package com.tap.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tap.repository.YoutubeResponse
import com.tap.repository.entities.Video
import com.tap.repository.useCases.SearchUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val actionMutableLiveData = MutableLiveData<Action>()
    val actionLiveData: LiveData<Action>
        get() = actionMutableLiveData

    fun search(query: String) {
        if (query.isEmpty()) {
            return
        }

        actionMutableLiveData.value = Action.StartLoading

        viewModelScope.launch {
            val youtubeResponse = searchUseCase(query)
            actionMutableLiveData.value = when (youtubeResponse) {
                is YoutubeResponse.Failed -> Action.ShowError(youtubeResponse.errorMessage)
                is YoutubeResponse.Success -> Action.ShowVideoList(youtubeResponse.videosList)
            }
        }
    }

    sealed class Action {
        object StartLoading : Action()

        data class ShowError(
            val error: String
        ) : Action()

        data class ShowVideoList(
            val videosList: List<Video>
        ) : Action()
    }
}
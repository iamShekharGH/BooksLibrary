package com.iamshekhargh.bookslibrary.ui.viewAllResults

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.iamshekhargh.bookslibrary.repo.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by <<-- iamShekharGH -->>
 * on 07 July 2021, Wednesday
 * at 11:02 PM
 */
@HiltViewModel
class ViewAllResultsViewModel @Inject constructor(private val repository: BooksRepository) :
    ViewModel() {
    private val eventsChannel = Channel<Events>()
    val events = eventsChannel.receiveAsFlow()

    private val internetChannel = Channel<Boolean>()
    val internetFlow = internetChannel.receiveAsFlow()

    val resultFlow = internetFlow.flatMapLatest { internet ->
        repository.getBooksResult(internet)
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)

    fun setInternetStatus(status: Boolean) = viewModelScope.launch {
        internetChannel.send(status)
    }

//    var internetToken = true
//    val resultFlow = repository.getBooksResult(internetFlow.asLiveData().value ?: true)


}

sealed class Events {
    data class ShowSnackbar(val text: String) : Events()
    data class InternetConnectionToggle(val netYn: Boolean) : Events()
}

enum class Internet {
    ON, OFF
}
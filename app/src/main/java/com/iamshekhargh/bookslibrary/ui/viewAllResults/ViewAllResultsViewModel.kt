package com.iamshekhargh.bookslibrary.ui.viewAllResults

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamshekhargh.bookslibrary.repo.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
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

    var internetToken = false

    val resultFlow = repository.getBooksResult(internetToken)

}

sealed class Events {
    data class ShowSnackbar(val text: String) : Events()
    data class InternetConnectionToggle(val netYn: Boolean) : Events()
}
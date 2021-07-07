package com.iamshekhargh.bookslibrary.ui.viewAllBooks

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

/**
 * Created by <<-- iamShekharGH -->>
 * on 08 July 2021, Thursday
 * at 12:30 AM
 */
@HiltViewModel
class FragmentBooksViewModel @Inject constructor() : ViewModel() {
    private val eventChannel = Channel<FragmentEvents>()
    val events = eventChannel.receiveAsFlow()

}

sealed class FragmentEvents {
    data class ShowSnackbar(val text: String) : FragmentEvents()
}
package com.iamshekhargh.bookslibrary.ui.firstFragment

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * Created by <<-- iamShekharGH -->>
 * on 05 July 2021, Monday
 * at 10:49 PM
 */
class FragmentFirstViewModel : ViewModel() {
    private val events = Channel<FirstFragEvents>()
    val eventsAsFlow = events.receiveAsFlow()
}

sealed class FirstFragEvents {
    data class ShowSnackBar(val text: String) : FirstFragEvents()
}
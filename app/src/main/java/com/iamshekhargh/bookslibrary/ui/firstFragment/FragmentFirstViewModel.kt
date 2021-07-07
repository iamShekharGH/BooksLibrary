package com.iamshekhargh.bookslibrary.ui.firstFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * Created by <<-- iamShekharGH -->>
 * on 05 July 2021, Monday
 * at 10:49 PM
 */
class FragmentFirstViewModel : ViewModel() {
    private val events = Channel<FirstFragEvents>()
    val eventsAsFlow = events.receiveAsFlow()

    fun addProductClicked() = viewModelScope.launch {
        events.send(FirstFragEvents.OpenAddProductFrag)
    }
}

sealed class FirstFragEvents {
    data class ShowSnackBar(val text: String) : FirstFragEvents()
    object OpenAddProductFrag : FirstFragEvents()
}
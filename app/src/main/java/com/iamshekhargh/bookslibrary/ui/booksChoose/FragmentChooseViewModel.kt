package com.iamshekhargh.bookslibrary.ui.booksChoose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamshekhargh.bookslibrary.repo.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

/**
 * Created by <<-- iamShekharGH -->>
 * on 08 July 2021, Thursday
 * at 1:01 AM
 */
@HiltViewModel
class FragmentChooseViewModel @Inject constructor(private val repository: BooksRepository) :
    ViewModel() {

    private val eventsChannel = Channel<Events>()
    val events = eventsChannel.receiveAsFlow()


    fun addProductClicked() = viewModelScope.launch {
        eventsChannel.send(Events.OpenAddProductsFragment)
    }

    fun syncProductClicked() {

        CoroutineScope(Dispatchers.IO).launch {
            eventsChannel.send(Events.ProgressBarShow(true))
            val itemsSyncked = repository.syncProducts()
            eventsChannel.send(Events.ShowSnackbar("$itemsSyncked products have been synced"))
            eventsChannel.send(Events.ProgressBarShow(false))
        }
    }

    fun skipClicked() = viewModelScope.launch {
        eventsChannel.send(Events.OpenProductListFragment)
    }
}

sealed class Events {
    data class ShowSnackbar(val text: String) : Events()
    object OpenAddProductsFragment : Events()
    object OpenProductListFragment : Events()
    data class ProgressBarShow(val show: Boolean) : Events()

}
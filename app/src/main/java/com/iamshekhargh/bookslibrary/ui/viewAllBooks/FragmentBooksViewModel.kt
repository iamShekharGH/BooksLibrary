package com.iamshekhargh.bookslibrary.ui.viewAllBooks

import androidx.lifecycle.*
import com.iamshekhargh.bookslibrary.db.Book
import com.iamshekhargh.bookslibrary.repo.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by <<-- iamShekharGH -->>
 * on 08 July 2021, Thursday
 * at 12:30 AM
 */
@HiltViewModel
class FragmentBooksViewModel @Inject constructor(private val repository: BooksRepository) :
    ViewModel() {

    private val eventChannel = Channel<FragmentEvents>()
    val events = eventChannel.receiveAsFlow()

    val books = MutableLiveData<List<Book>>()

    fun getBooksFromDB(): LiveData<List<Book>> {
        return repository.booksFlow.asLiveData()
    }

    fun fetchFromDb() = viewModelScope.launch {
        repository.getAllBooks()
    }

}

sealed class FragmentEvents {
    data class ShowSnackbar(val text: String) : FragmentEvents()
}
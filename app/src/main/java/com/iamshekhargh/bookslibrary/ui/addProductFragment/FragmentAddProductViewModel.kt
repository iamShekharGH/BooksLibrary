package com.iamshekhargh.bookslibrary.ui.addProductFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamshekhargh.bookslibrary.db.Book
import com.iamshekhargh.bookslibrary.network.module.res.Result
import com.iamshekhargh.bookslibrary.repo.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by <<-- iamShekharGH -->>
 * on 07 July 2021, Wednesday
 * at 7:01 PM
 */
@HiltViewModel
class FragmentAddProductViewModel @Inject constructor(val repository: BooksRepository) :
    ViewModel() {

    private val eventsChannel = Channel<AddProductEvents>()
    val events = eventsChannel.receiveAsFlow()

    fun saveClicked(
        productName: String,
        productDescription: String,
        producePrice: String,
        productQuantity: String
    ) =
        viewModelScope.launch {
            if (productName.isNullOrEmpty()) {
                eventsChannel.send(AddProductEvents.ShowSnackbar("Product Name cannot be Empty"))
            } else if (producePrice.isNullOrEmpty()) {
                eventsChannel.send(AddProductEvents.ShowSnackbar("Product Price cannot be Empty"))
            } else if (productQuantity.isNullOrEmpty()) {
                eventsChannel.send(AddProductEvents.ShowSnackbar("Product Quantity cannot be Empty"))
            } else {

                val b = Book(
                    productDescription,
                    productName,
                    producePrice.toInt(),
                    productQuantity.toInt(),
                    "9876543210"
                )
                repository.insertBook(b)
                eventsChannel.send(AddProductEvents.PopTheFragment)
            }

        }

}

sealed class AddProductEvents {
    data class ShowSnackbar(val text: String) : AddProductEvents()
    object PopTheFragment : AddProductEvents()

}
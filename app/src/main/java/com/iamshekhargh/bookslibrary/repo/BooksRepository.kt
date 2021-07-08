package com.iamshekhargh.bookslibrary.repo

import androidx.lifecycle.asLiveData
import com.iamshekhargh.bookslibrary.db.Book
import com.iamshekhargh.bookslibrary.db.BooksDao
import com.iamshekhargh.bookslibrary.db.ResultDao
import com.iamshekhargh.bookslibrary.network.ApiInterface
import com.iamshekhargh.bookslibrary.util.networkBoundResources
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by <<-- iamShekharGH -->>
 * on 05 July 2021, Monday
 * at 10:40 PM
 */
class BooksRepository @Inject constructor(
    private val apiInterface: ApiInterface,
    private val daoBooks: BooksDao,
    private val daoResults: ResultDao

) {

    lateinit var booksFlow: Flow<List<Book>>

    fun getBooksResult(internetToken: Boolean) = networkBoundResources(
        dbSearch = {
            daoResults.getAllResult()
        },
        apiCall = {
            apiInterface.getAllAvailableBooks()
        },
        saveToDb = { response ->
            response.results.forEach { result ->
                daoResults.insertResult(result)
            }
        },
        shouldFetch = {
            internetToken
        }
    )

    suspend fun insertBook(b: Book) {
        daoBooks.insertBook(b)

    }

    suspend fun getAllBooks() {
        booksFlow = daoBooks.getAllBooks()
    }

    suspend fun syncProducts(): Int {
        val buksList = daoBooks.getAllBooksList()
        var listLength = 0

        buksList.forEach { buk ->
            val response = apiInterface.postProductInfo(buk)
            if (response.results.success == 1) {
                //Success
                listLength++
            } else {
                //failed
            }
        }

        return listLength
    }

    suspend fun postInformation(booklist: List<Book>) {
        booklist.forEach { buk ->
            apiInterface.postProductInfo(buk)
        }

    }
}
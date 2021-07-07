package com.iamshekhargh.bookslibrary.repo

import com.iamshekhargh.bookslibrary.db.Book
import com.iamshekhargh.bookslibrary.db.BooksDao
import com.iamshekhargh.bookslibrary.network.ApiInterface
import javax.inject.Inject

/**
 * Created by <<-- iamShekharGH -->>
 * on 05 July 2021, Monday
 * at 10:40 PM
 */
class BooksRepository @Inject constructor(
    apiInterface: ApiInterface,
    val dao: BooksDao
) {
    suspend fun insertBook(b: Book) {
        dao.insertBook(b)
    }


}
package com.iamshekhargh.bookslibrary.db

import androidx.room.*
import com.iamshekhargh.bookslibrary.network.module.res.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by <<-- iamShekharGH -->>
 * on 07 July 2021, Wednesday
 * at 9:40 PM
 */
@Dao
interface BooksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(b: Book)

    @Delete
    suspend fun deleteBook(b: Book)

    @Update
    suspend fun updateBook(b: Book)

    @Query("SELECT * FROM books_table")
    fun getAllBooks(): Flow<List<Book>>

}
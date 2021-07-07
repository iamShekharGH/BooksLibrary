package com.iamshekhargh.bookslibrary.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iamshekhargh.bookslibrary.network.module.res.Result

/**
 * Created by <<-- iamShekharGH -->>
 * on 07 July 2021, Wednesday
 * at 5:09 PM
 */
@Database(entities = [Result::class, Book::class], version = 1, exportSchema = false)
abstract class BooksDatabase : RoomDatabase() {

    abstract fun getResultDao(): ResultDao

    abstract fun getBooksDao(): BooksDao
}
package com.iamshekhargh.bookslibrary.db

import androidx.room.*
import com.iamshekhargh.bookslibrary.network.module.res.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by <<-- iamShekharGH -->>
 * on 07 July 2021, Wednesday
 * at 5:01 PM
 */
@Dao
interface ResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(b: Result)

    @Delete
    suspend fun deleteResult(b: Result)

    @Update
    suspend fun updateResult(b: Result)

    @Query("SELECT * FROM results_table")
    fun getAllResult(): Flow<List<Result>>
}
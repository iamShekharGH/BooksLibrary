package com.iamshekhargh.bookslibrary.network

import com.iamshekhargh.bookslibrary.db.Book
import com.iamshekhargh.bookslibrary.network.module.res.BookList
import com.iamshekhargh.bookslibrary.network.module.res.PostResponse
import com.iamshekhargh.bookslibrary.network.module.res.Result
import com.iamshekhargh.bookslibrary.util.Resource
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Created by <<-- iamShekharGH -->>
 * on 05 July 2021, Monday
 * at 10:37 PM
 */
interface ApiInterface {
    companion object {
        const val BASE_URL = ""
    }

    @Headers("Content-Type: application/json")
    @GET("api/getAllAvailableBooks/")
    suspend fun getAllAvailableBooks(): BookList

    @Headers("Content-Type: application/json")
    @POST("api/addNewProduct/")
    suspend fun postProductInfo(@Body b: Book): PostResponse
}
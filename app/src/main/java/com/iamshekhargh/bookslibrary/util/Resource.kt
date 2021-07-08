package com.iamshekhargh.bookslibrary.util

/**
 * Created by <<-- iamShekharGH -->>
 * on 08 July 2021, Thursday
 * at 1:33 AM
 */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)

    class Loading<T>(data: T? = null) : Resource<T>(data)

    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}
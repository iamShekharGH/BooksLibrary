package com.iamshekhargh.bookslibrary.util

import kotlinx.coroutines.flow.*

/**
 * Created by <<-- iamShekharGH -->>
 * on 08 July 2021, Thursday
 * at 1:39 AM
 */

fun <ResultType, RequestType> networkBoundResources(
    dbSearch: () -> Flow<ResultType>,
    apiCall: suspend () -> RequestType,
    saveToDb: suspend (RequestType) -> Unit,
    shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = dbSearch().first()
    val resFlow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))
        try {
            saveToDb(apiCall())
            dbSearch().map {
                Resource.Success(it)
            }
        } catch (t: Throwable) {
            dbSearch().map {
                t.printStackTrace()
                Resource.Error("Failed To load coz ${t.message}", it)
            }
        }
    } else {
        dbSearch().map {
            Resource.Success(it)
        }
    }
    emitAll(resFlow)
}
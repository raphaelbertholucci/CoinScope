package com.coinscope.data.helpers

import com.coinscope.domain.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.net.UnknownHostException


fun <T> safeApiCall(
    apiCall: suspend (Boolean) -> T
): Flow<ResultWrapper<T>> {
    return flow {
        val result = try {
            ResultWrapper.Success(apiCall.invoke(true))
        } catch (throwable: Throwable) {
//            Log.d("Crash: ", throwable.message.toString())
            when (throwable) {
                is UnknownHostException -> ResultWrapper.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    ResultWrapper.Error(code, throwable)
                }

                else -> ResultWrapper.Error(
                    code = 500,
                    error = Throwable(message = "Undefined error!")
                )
            }
        }
        emit(result)
    }
}

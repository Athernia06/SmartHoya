package com.example.myapplication.core.util.handler

import com.google.gson.JsonSyntaxException
import com.example.myapplication.core.util.constant.ErrorConstant.ERROR_FAILED_BODY_CONVERSION
import com.example.myapplication.core.util.wrapper.CoreDataWrapper

interface IRemoteSourceHandler {
    fun <L, R> handleCatchRemoteSource(e: Exception): CoreDataWrapper<L, R>
    fun <L, R> handleCommonUnsuccessfulRemoteSource(
        errorCode: Int,
        errorString: String?
    ): CoreDataWrapper<L, R>
}

object RemoteSourceHandler :
    IRemoteSourceHandler {
    override fun <L, R> handleCatchRemoteSource(e: Exception): CoreDataWrapper<L, R> {
        when (e) {
            //When Connection categorized as network error
            is JsonSyntaxException -> {
                return CoreDataWrapper.Error(ERROR_FAILED_BODY_CONVERSION)
            }

            is java.net.SocketTimeoutException -> {
                return CoreDataWrapper.NetworkError
            }

            is java.net.UnknownHostException -> {
                return CoreDataWrapper.NetworkError
            }

            else -> {
                return CoreDataWrapper.Error(e.message.toString())
            }
        }
    }

    override fun <L, R> handleCommonUnsuccessfulRemoteSource(
        errorCode: Int,
        errorString: String?
    ): CoreDataWrapper<L, R> {
        if (errorCode == 401) {
            return CoreDataWrapper.UnauthorizedError
        }
        return CoreDataWrapper.Error(errorString ?: "Unkown Error", errorCode, "Unkown Error")
    }
}
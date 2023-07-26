package com.example.myapplication.core.util.wrapper

sealed class CoreDataWrapper<out L, out R> {
    data class Success<out R>(val data: R) : CoreDataWrapper<Nothing, R>()
    data class ErrorBody<out L>(val errorData: L) : CoreDataWrapper<L, Nothing>()
    data class Error(
        val errorMessage: String,
        val errorCode: Int? = null,
        val errorType: String? = null,
    ) : CoreDataWrapper<Nothing, Nothing>()

    object NetworkError : CoreDataWrapper<Nothing, Nothing>()
    object UnauthorizedError : CoreDataWrapper<Nothing, Nothing>()
}
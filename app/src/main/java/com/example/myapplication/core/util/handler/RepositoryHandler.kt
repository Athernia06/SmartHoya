package com.example.myapplication.core.util.handler

import com.example.myapplication.core.util.wrapper.CoreDataWrapper

interface IRepositoryHandler {
    fun <L, R, NR> handleRepositoryNotSuccessful(remoteWrapperResult: CoreDataWrapper<L, R>): CoreDataWrapper<L, NR>
}


object RepositoryHandler : IRepositoryHandler {
    override fun <L, R, NR> handleRepositoryNotSuccessful(remoteWrapperResult: CoreDataWrapper<L, R>): CoreDataWrapper<L, NR> {
        when (remoteWrapperResult) {
            is CoreDataWrapper.Success -> {
                return CoreDataWrapper.Error("Wrong Success Handling")
            }

            is CoreDataWrapper.ErrorBody -> {
                return remoteWrapperResult
            }

            is CoreDataWrapper.Error -> {
                return remoteWrapperResult
            }

            is CoreDataWrapper.NetworkError -> {
                return remoteWrapperResult
            }

            is CoreDataWrapper.UnauthorizedError -> {
                return remoteWrapperResult
            }
        }
    }
}
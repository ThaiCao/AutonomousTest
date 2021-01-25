package com.app.autonomoustesting.core.repository.utils

import com.app.autonomoustesting.core.model.ErrorResponse

sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val error: ErrorResponse) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$error]"
        }
    }
}
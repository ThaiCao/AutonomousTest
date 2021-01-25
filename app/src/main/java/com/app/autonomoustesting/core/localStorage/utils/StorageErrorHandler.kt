package com.app.autonomoustesting.core.localStorage.utils

import android.content.Context
import com.app.autonomoustesting.core.model.ErrorResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StorageErrorHandler @Inject constructor(context: Context) {

    private val generalError: String = context.getString(com.app.autonomoustesting.R.string.storage_error_message)

    fun createGeneralError(): ErrorResponse {
        return ErrorResponse(generalError)
    }

}
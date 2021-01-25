package com.app.autonomoustesting.core.memoryCache.utils

import android.content.Context
import com.app.autonomoustesting.core.model.ErrorResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemoryCacheErrorHandler @Inject constructor(context: Context) {

    private val generalError: String = context.getString(com.app.autonomoustesting.R.string.memory_error_message)

    fun createGeneralError(): ErrorResponse {
        return ErrorResponse(generalError)
    }

}
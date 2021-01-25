package com.app.autonomoustesting.core.repository.utils

import com.app.autonomoustesting.R
import android.content.Context
import com.app.autonomoustesting.core.model.ErrorResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryErrorHandler  @Inject constructor(context: Context) {

    private val generalError: String = context.getString(R.string.general_error_message)

    fun createGeneralError(): ErrorResponse {
        return ErrorResponse(generalError)
    }

}
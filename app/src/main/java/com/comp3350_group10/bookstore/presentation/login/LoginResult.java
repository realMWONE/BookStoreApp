package com.comp3350_group10.bookstore.presentation.login;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (user details) or error message.
 */
class LoginResult
{
    @Nullable
    private LoggedInUserView success;
    @Nullable
    private Integer error;

    LoginResult(@Nullable Integer error)
    {
        this.error = error;
    }

    LoginResult(@Nullable LoggedInUserView success)
    {
        this.success = success;
    }

    @Nullable
    LoggedInUserView getSuccess()
    {
        return success;
    }

    @Nullable
    Integer getError()
    {
        return error;
    }
}
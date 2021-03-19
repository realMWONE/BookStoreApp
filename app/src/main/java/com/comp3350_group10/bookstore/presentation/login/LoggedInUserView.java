package com.comp3350_group10.bookstore.presentation.login;

/**
 * Class exposing authenticated user details to the UI.
 */
class LoggedInUserView
{
    private final String displayName;
    //... other data fields that may be accessible to the UI

    LoggedInUserView(String displayName)
    {
        this.displayName = displayName;
    }

    String getDisplayName()
    {
        return displayName;
    }
}
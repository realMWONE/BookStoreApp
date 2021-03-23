package com.comp3350_group10.bookstore.persistence.fakeDB;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser
{

    private String userId;
    private String displayName;

    public LoggedInUser(String userId, String displayName)
    {
        this.userId = userId;
        this.displayName = displayName;
    }

    public String getUserId()
    {
        return userId;
    }

    public String getDisplayName()
    {
        return displayName;
    }
}
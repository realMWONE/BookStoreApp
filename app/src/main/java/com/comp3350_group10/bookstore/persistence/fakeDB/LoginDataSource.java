package com.comp3350_group10.bookstore.persistence.fakeDB;
<<<<<<< HEAD:app/src/main/java/com/comp3350_group10/bookstore/persistence/fakeDB/LoginDataSource.java
=======

>>>>>>> 022f4f1b6a297a18a55ac4136faa911b12c701c8:app/src/main/java/com/comp3350_group10/bookstore/data/LoginDataSource.java

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource
{

    public Result<LoggedInUser> login(String username, String password)
    {
        try
        {
            // TODO: handle loggedInUser authentication
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            return new Result.Success<>(fakeUser);
        } catch (Exception e)
        {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout()
    {
        // TODO: revoke authentication
    }
}
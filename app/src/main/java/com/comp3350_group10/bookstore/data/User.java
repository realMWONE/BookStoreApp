package com.comp3350_group10.bookstore.data;

import com.comp3350_group10.bookstore.UserType;

public class User 
{

    private String realName;
    private String userID; //email
    private String password;
    private UserType position;

    public User(String name, String userId, String password, UserType type)
    {
        this.realName = name;
        this.userID = userId;
        this.password = password;
        this.position = type;
    }

    public String getRealName()
    {

        return realName;
    }

    public UserType getUserType()
    {

        return position;
    }

    public String getUserID()
    {

        return userID;
    }

    public String getPassword()
    {

        return password;
    }

    public void setUserID(String userId)
    {

        userID = userId;
    }

    public void setPassword(String newPassword)
    {

        password = newPassword;
    }

}

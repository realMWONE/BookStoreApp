package com.comp3350_group10.bookstore.business;

import com.comp3350_group10.bookstore.persistence.IBook;

import java.util.List;


public interface IUserDataHandler {

    //function to check whether the current user is a manager or employee
    boolean isCurrentUserManager();

    //function to logout the current user
    void logOut();

    void logIn(String email, String password) throws ClassNotFoundException;

    //function to changePassword for the logged in user
    void changePassword(String oldPw, String newPw, String confirmNewPw);
}

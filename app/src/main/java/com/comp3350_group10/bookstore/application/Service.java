package com.comp3350_group10.bookstore.application;

import com.comp3350_group10.bookstore.persistence.IBookDatabase;
import com.comp3350_group10.bookstore.persistence.IUserDatabase;
import com.comp3350_group10.bookstore.persistence.hsqldb.BookDatabase;
import com.comp3350_group10.bookstore.persistence.hsqldb.UserDatabase;

public class Service {
    private static boolean bookHsqlActivated = false;
    private static boolean userHsqlActivated = false;

    private static IBookDatabase bookPersistance = null;
    private static IUserDatabase userPersistance = null;

    public static synchronized IBookDatabase setupBookDatabase(){
        //if not activated the database, we will instantiate that
        if(bookHsqlActivated==false){
            bookPersistance = new BookDatabase(Main.getDBPath());
            bookHsqlActivated=true;
        }
        return bookPersistance;
    }

    public static synchronized IUserDatabase setupUserDatabase(){
        if(userHsqlActivated==false){
            userPersistance = new UserDatabase(Main.getDBPath());
            userHsqlActivated=true;
        }
        return userPersistance;
    }


}

package com.comp3350_group10.bookstore;

import com.comp3350_group10.bookstore.data.User;

public class UserDatabase {

    private static User[] userList;

    static void addUser(User newUser){

        User[] newArray = new User[userList.length + 1];

        for(int i = 0; i < userList.length; i++){
            if(userList[i] == null){
                newArray[i] = newUser;
            }else{
                newArray[i] = userList[i];
            }
        }
        newArray[newArray.length-1] = newUser;
    }
}

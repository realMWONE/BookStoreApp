package com.comp3350_group10.bookstore.persistence;

import java.util.List;

public interface IUserDatabase {
    IUser findUser(String userId) throws ClassNotFoundException;
    /*we don't need this, we only need to findUser. And findUser beforehand
    will execute the getUsers
    List<IUser> getUsers();
     */
    IUser createUser(IUser user) throws ClassNotFoundException;
    IUser updateUser(IUser user) throws ClassNotFoundException;
    void deleteUser(IUser user) throws ClassNotFoundException;
}

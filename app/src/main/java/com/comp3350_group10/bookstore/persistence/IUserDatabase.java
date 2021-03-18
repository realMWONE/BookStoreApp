package com.comp3350_group10.bookstore.persistence;

import java.util.List;

public interface IUserDatabase {
    IUser findUser(String userId);
    /*we don't need this, we only need to findUser. And findUser beforehand
    will execute the getUsers
    List<IUser> getUsers();
     */
    IUser createUser(IUser user);
    void updateUser(IUser user);
    void deleteUser(IUser user);
}

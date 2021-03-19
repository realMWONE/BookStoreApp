package com.comp3350_group10.bookstore.persistence;

public interface IUserDatabase {
    IUser findUser(String userId) throws ClassNotFoundException;
    /*we don't need this, we only need to findUser. And findUser beforehand
    will execute the getUsers
    List<IUser> getUsers();
     */
    IUser createUser(IUser user) throws ClassNotFoundException;
    void updateUser(IUser user) throws ClassNotFoundException;
    void deleteUser(IUser user) throws ClassNotFoundException;
}

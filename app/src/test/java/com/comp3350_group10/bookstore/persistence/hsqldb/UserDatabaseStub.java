package com.comp3350_group10.bookstore.persistence.hsqldb;

import com.comp3350_group10.bookstore.objects.User;
import com.comp3350_group10.bookstore.persistence.IUser;
import com.comp3350_group10.bookstore.persistence.IUserDatabase;
import com.comp3350_group10.bookstore.persistence.UserType;

import java.util.ArrayList;
import java.util.List;

public class UserDatabaseStub implements IUserDatabase {

    private List<IUser> userList;

    public UserDatabaseStub(){
        this.userList = new ArrayList<>();
        userList.add(new User("Daniel","duy.than@gihot.com","111", UserType.Employee));
        userList.add(new User("Keven","Kevin@gmail.com","222", UserType.Manager));
        userList.add(new User("Harshal","Harshal@myumanitoba.ca","333", UserType.Manager));
        userList.add(new User("Matt","Matt@yahoo.com","444", UserType.Employee));
        userList.add(new User("Animesh","Animesh@outlook.com","555", UserType.Employee));
        userList.add(new User("Darwait","Darwait@gmail.com","666", UserType.Employee));
    }

    @Override
    public IUser findUser(String userId){
        for(int i = 0;i < userList.size(); i++){
            if(userList.get(i).getUserID().equals(userId)) {
                return userList.get(i);
            }
        }
        return null;
    }

    @Override
    public IUser insertUser(IUser user){
        userList.add(user);
        return user;
    }

    @Override
    public IUser updateUser(IUser user){
        int index;

        index = userList.indexOf(user);
        if(index >= 0){
            userList.set(index, user);
        }

    }

    @Override
    public void deleteUser(IUser user){
        IUser delete=null;
        for(int i=0;i<list.size();i++){
            if(list.get(i).getUserID().equals(user.getUserID())){
                delete=list.get(i);
                break;
            }
        }
        //if nothing to delete
        if(delete==null)
            return;
        list.remove(delete);
    }
}

package com.comp3350_group10.bookstore.persistence.hsqldb;

import com.comp3350_group10.bookstore.objects.User;
import com.comp3350_group10.bookstore.persistence.IUser;
import com.comp3350_group10.bookstore.persistence.IUserDatabase;
import com.comp3350_group10.bookstore.persistence.UserType;

import java.util.ArrayList;
import java.util.List;

public class UserDatabaseStub implements IUserDatabase {
    private List<IUser> list;
    public UserDatabaseStub(){
        this.list = new ArrayList<>();
        list.add(new User("Daniel","duy.than@gihot.com"
                ,"111", UserType.Employee));
        list.add(new User("Keven","Kevin@gmail.com"
                ,"222", UserType.Manager));
        list.add(new User("Harshal","Harshal@myumanitoba.ca"
                ,"333", UserType.Manager));
        list.add(new User("Matt","Matt@yahoo.com"
                ,"444", UserType.Employee));
        list.add(new User("Animesh","Animesh@outlook.com"
                ,"555", UserType.Employee));
        list.add(new User("Darwait","Darwait@gmail.com"
                ,"666", UserType.Employee));
    }

    @Override
    public IUser findUser(String userId) throws ClassNotFoundException {
        for(int i=0;i<list.size();i++){
            if(list.get(i).getUserID().equals(userId))
                return list.get(i);
        }
        return null;
    }

    @Override
    public IUser insertUser(IUser user) throws ClassNotFoundException {
        list.add(user);
        return user;
    }

    @Override
    public IUser updateUser(IUser user) throws ClassNotFoundException {
        IUser result = null;
        for(int i=0;i<list.size();i++){
            if(list.get(i).getUserID().equals(user.getUserID())){
                result=list.get(i);
                result.setName(user.getRealName());
                result.setPassword(user.getPassword());
                result.setPosition(user.getUserType());
                return user;
            }
        }
        return null;
    }

    @Override
    public void deleteUser(IUser user) throws ClassNotFoundException {
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

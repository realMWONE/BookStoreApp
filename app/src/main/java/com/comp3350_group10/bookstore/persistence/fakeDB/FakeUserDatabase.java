package com.comp3350_group10.bookstore.persistence.fakeDB;

import com.comp3350_group10.bookstore.objects.User;
import com.comp3350_group10.bookstore.persistence.IUser;
import com.comp3350_group10.bookstore.persistence.IUserDatabase;
import com.comp3350_group10.bookstore.persistence.UserType;

import java.util.ArrayList;
import java.util.List;

public class FakeUserDatabase implements IUserDatabase {
    private final List<IUser> userList = new ArrayList<>();

    public FakeUserDatabase(){
        userList.add(new User("Kevin","Kevin@gmail.com","12345678", UserType.Manager));
        userList.add(new User("Harshal","Harshall@umanitoba.ca","12345678",UserType.Manager));
        userList.add(new User("Matt","Matt@yahoo.com","12345678",UserType.Employee));
        userList.add(new User("Daniel","duy.than@gihot.com","12345678",UserType.Employee));
        userList.add(new User("Animesh","Animesh@outlook.com","12345678",UserType.Employee));
        userList.add(new User("Darwait","Darwait@gmail.com","12345678",UserType.Employee));
    }

    @Override
    public IUser findUser(String userId) throws ClassNotFoundException {
        IUser user = null;
        if(userList.size()>0){
            for(int i=0;i<userList.size();i++){
                if(userList.get(i).getUserID().toLowerCase().equals(userId))
                    user = userList.get(i);
            }
        }
        return user;
    }

    @Override
    public IUser insertUser(IUser user) throws ClassNotFoundException {
        userList.add(user);
        return user;
    }

    @Override
    public IUser updateUser(IUser user) throws ClassNotFoundException {
        for(IUser u:userList){
            if(u.getUserID().equals(user.getUserID())) {
                u = user;
                return u;
            }
        }
        return null;
    }

    @Override
    public void deleteUser(IUser user) throws ClassNotFoundException {
        for(IUser u:userList){
            if(u.getUserID().equals(user.getUserID()))
                userList.remove(u);
        }
    }
}

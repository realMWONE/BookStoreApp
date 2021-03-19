package com.comp3350_group10.bookstore.persistence.fakeDB;

import com.comp3350_group10.bookstore.persistence.IUser;
import com.comp3350_group10.bookstore.persistence.IUserDatabase;

import java.util.ArrayList;
import java.util.List;

public class FakeUserDatabase implements IUserDatabase {
    private List<IUser> userList;

    public FakeUserDatabase(){
        userList = new ArrayList<>();
    }

    @Override
    public IUser findUser(String userId) throws ClassNotFoundException {
        IUser user = null;
        if(userList.size()>0){
            for(int i=0;i<userList.size();i++){
                if(userList.get(i).getUserID().equals(userId))
                    user = userList.get(i);
            }
        }
        return user;
    }

    @Override
    public IUser createUser(IUser user) throws ClassNotFoundException {
        userList.add(user);
        return user;
    }

    @Override
    public void updateUser(IUser user) throws ClassNotFoundException {
        for(IUser u:userList){
            if(u.getUserID().equals(user.getUserID()))
                u = user;
        }
    }

    @Override
    public void deleteUser(IUser user) throws ClassNotFoundException {
        for(IUser u:userList){
            if(u.getUserID().equals(user.getUserID()))
                userList.remove(u);
        }
    }
}

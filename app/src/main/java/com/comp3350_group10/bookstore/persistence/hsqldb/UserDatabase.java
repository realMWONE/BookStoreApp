package com.comp3350_group10.bookstore.persistence.hsqldb;


import com.comp3350_group10.bookstore.objects.Book;
import com.comp3350_group10.bookstore.objects.User;
import com.comp3350_group10.bookstore.persistence.IBook;
import com.comp3350_group10.bookstore.persistence.IUser;
import com.comp3350_group10.bookstore.persistence.IUserDatabase;
import com.comp3350_group10.bookstore.persistence.UserType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDatabase implements IUserDatabase {

    private final String dbPath;
    private final String MANAGER="MANAGER";
    private final String EMPLOYEE="EMPLOYEE";

    private List<IUser> userList;

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:"+ dbPath+ ";shutdown=true", "SA", "");
    }

    private User createUser(final ResultSet rs) throws SQLException{
        final String name = rs.getString("name");
        final String userId = rs.getString("userId");
        final String password = rs.getString("password");
        final String position = rs.getString("position");
        return new User(name,userId,password,position==MANAGER?UserType.Manager:UserType.Employee);
    }

    public UserDatabase(final String dbPath){
        this.dbPath = dbPath;
    }

    @Override
    public IUser findUser(String userId) {
        //retrieve getUsers first
        userList = getUsers();
        if(userList.size()==0)
            return null;
        for(int i=0;i<userList.size();i++){
            if(userList.get(i).getUserID().equals(userId))
                return userList.get(i);
        }
        return null;
    }

    private List<IUser> getUsers() {
        final List<IUser> usersInfo = new ArrayList<>();

        try (final Connection conn = connection()){
            final Statement stmt = conn.createStatement();
            final ResultSet rtst = stmt.executeQuery("SELECT * FROM USERS");

            while(rtst.next()){
                final User user = createUser(rtst);
                usersInfo.add(user);
            }

            rtst.close();
            stmt.close();
        }
        catch(final SQLException e){
            throw new PersistenceException(e);
        }
        return usersInfo;
    }

    @Override
    public IUser createUser(IUser user) {
        try(final Connection conn = connection()) {
            final PreparedStatement pstmt = conn.prepareStatement("INSERT INTO USERS VALUES(?,?,?,?)");
            pstmt.setString(1, user.getRealName());
            pstmt.setString(2, user.getUserID());
            pstmt.setString(3, user.getPassword());
            String position= user.getUserType()==UserType.Employee?EMPLOYEE:MANAGER;
            pstmt.setString(4, position);
            pstmt.executeUpdate();
            return user;
        }
        catch(final SQLException e){
            throw new PersistenceException(e);
        }
    }

    @Override
    public void updateUser(IUser user) {
        try (final Connection conn = connection()){
            final PreparedStatement pstmt =
                    conn.prepareStatement("UPDATE USERS SET Name=?,password=?, position=? WHERE userId=?");
            pstmt.setString(1, user.getRealName());
            pstmt.setString(2, user.getPassword());
            String position = user.getUserType()==UserType.Employee?EMPLOYEE:MANAGER;
            pstmt.setString(3, position);
            pstmt.executeUpdate();
        }
        catch(final SQLException e){
            throw new PersistenceException(e);
        }
    }

    @Override
    public void deleteUser(IUser user) {
        try (final Connection conn = connection()){
            final PreparedStatement pstmt =
                    conn.prepareStatement("DELETE FROM USER WHERE userId=?");
            pstmt.setString(1, user.getUserID());
            pstmt.executeUpdate();
        }
        catch(final SQLException e){
            throw new PersistenceException(e);
        }
    }



    /*static void addUser(User newUser){

        User[] newArray = new User[userList.length + 1];

        for(int i = 0; i < userList.length; i++){
            if(userList[i] == null){
                newArray[i] = newUser;
            }else{
                newArray[i] = userList[i];
            }
        }
        newArray[newArray.length-1] = newUser;
    }*/


}

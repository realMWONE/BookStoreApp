package com.comp3350_group10.bookstore.persistence.hsqldb;


import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDatabase {

     /*private final String dbPath;
    private final String MANAGER="MANAGER";
    private final String EMPLOYEE="EMPLOYEE";

    private List<IUser> userList;*/

    /*private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:"+ dbPath+ ";shutdown=true", "SA", "");
    }*/

    private static Connection connection;
    private final String userTXT = "users.txt";
    private final String nameField = "name";
    private final String userIdField = "userId";
    private final String passwordField = "password";
    private final String positionField = "positon";
    private final String MANAGER = "Manager";
    private final String EMPLOYEE = "Employee";

    /*private User createUser(final ResultSet rs) throws SQLException{
        final String name = rs.getString("name");
        final String userId = rs.getString("userId");
        final String password = rs.getString("password");
        final String position = rs.getString("position");
        return new User(name,userId,password,position==MANAGER? UserType.Manager:UserType.Employee);
    }*/

    public UserDatabase(){
        //this.dbPath = dbPath;
        try{
            Class.forName("org.hsqldb.jdbcDriver");
            //creates an in-memory database
            connection  = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb", "SA", "");
            createTables();
            readInData();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace(System.out);
        }
        catch(SQLException e){
            e.printStackTrace(System.out);
        }
    }
    private void createTables(){
        //Creating table for books which would have the following attributes.
        String users = "create table users ( "+

                nameField+ " VARCHAR(30)," +
                userIdField+ " VARCHAR(30),"+
                passwordField+ " VARCHAR(30)," +
                positionField+ " VARCHAR(30)"+
                ");";
        try{
            connection.createStatement().executeUpdate(users);
        }
        catch(SQLException e){
            e.printStackTrace(System.out);
        }
    }

    private void readInData(){

        try{
            BufferedReader readUserTXT = null;
            readUserTXT = new BufferedReader((new FileReader(userTXT)));
            readUserTXT.readLine();

            String userLine = readUserTXT.readLine();

            while(userLine != null){
                String[] userPart = userLine.trim().split(",");
                createUsers(userPart[0],userPart[1],userPart[2],userPart[3]);
                userLine = readUserTXT.readLine();
            }
            readUserTXT.close();
        }
        catch (Exception e){
            //Log.d("error in UserDatabase",e.toString());
        }


    }

    private String createUsers(String name, String userId, String password, String position){
        String userIdValue = "";
        int result = 0;
        try{
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT "+userIdField+" FROM users WHERE "+userIdField+"= ?;"
            );
            pstmt.setString(1, userId);
            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                userIdValue = resultSet.getString(userIdField);
                if(userIdValue.equals(userId)){
                    userIdValue = "This already exists";
                }
            }
            else{
                PreparedStatement addUserId = connection.prepareStatement(
                        "INSERT INTO users ("+nameField+","+userIdField+","+passwordField+","+positionField+") VALUES (?, ?, ?, ?);"
                );
                addUserId.setString(1, name);
                addUserId.setString(2, userId);
                addUserId.setString(3, password);
                addUserId.setString(4, position);

                result = addUserId.executeUpdate();
                connection.commit();

                addUserId.close();
                resultSet.close();
            }
            pstmt.close();
        }
        catch(SQLException e){
            e.printStackTrace(System.out);
        }
        return userIdValue;
    }
    public void printAllUsers(){
        ResultSet resultSet = null;
        //ResultSet resultSet2 = null;
        //Statement stmt = null;
        //String result = "";
        try{
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users");
            resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                System.out.println("name:"+resultSet.getString(nameField));
                System.out.println("userId:"+resultSet.getString(userIdField));
                System.out.println("password:"+resultSet.getString(passwordField));
                System.out.println("position:"+resultSet.getString(positionField));
            }
            /*PreparedStatement pstmt2 = connection.prepareStatement("SELECT * FROM bookInfo");
            resultSet2 = pstmt2.executeQuery();
            while(resultSet2.next()){
                result = resultSet2.getString("isbn") + "," + resultSet2.getString("genre") + "," + resultSet2.getString("author");
                System.out.println(result);
            }
            stmt = connection.createStatement();
            int result4 = stmt.executeUpdate("DELETE FROM bookInfo WHERE isbn = 3214686513501");
            System.out.println(result4);

            System.out.println(result);*/
        }
        catch(SQLException e){
            e.printStackTrace(System.out);
        }

    }


    /*public void findUser(){
        ResultSet resultSet = null;
        ResultSet resultSet2 = null;
        Statement stmt = null;
        String result = "";
        try{
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users");
            resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getString("bookName"));
            }
            PreparedStatement pstmt2 = connection.prepareStatement("SELECT * FROM bookInfo");
            resultSet2 = pstmt2.executeQuery();
            while(resultSet2.next()){
                result = resultSet2.getString("isbn") + "," + resultSet2.getString("genre") + "," + resultSet2.getString("author");
                System.out.println(result);
            }
            stmt = connection.createStatement();
            int result4 = stmt.executeUpdate("DELETE FROM bookInfo WHERE isbn = 3214686513501");
            System.out.println(result4);

            System.out.println(result);
        }
        catch(SQLException e){
            e.printStackTrace(System.out);
        }

    }*/



    /*
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
    }*/


}

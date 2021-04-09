package com.comp3350_group10.bookstore.business;

import android.service.autofill.UserData;

import com.comp3350_group10.bookstore.Exceptions.ChangePasswordException;
import com.comp3350_group10.bookstore.Exceptions.CreateUserErrorException;
import com.comp3350_group10.bookstore.Exceptions.DifferentPasswordException;
import com.comp3350_group10.bookstore.Exceptions.UserNotFoundException;
import com.comp3350_group10.bookstore.objects.User;
import com.comp3350_group10.bookstore.persistence.IBookDatabase;
import com.comp3350_group10.bookstore.persistence.IUser;
import com.comp3350_group10.bookstore.persistence.IUserDatabase;
import com.comp3350_group10.bookstore.persistence.UserType;
import com.comp3350_group10.bookstore.persistence.hsqldb.BookDatabaseStub;
import com.comp3350_group10.bookstore.persistence.hsqldb.UserDatabaseStub;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserDataHandlerTest extends TestCase {

    //we will use a stub
    private UserDataHandler dataHandler;
    @Before
    public void setUp() throws Exception {
        super.setUp();
        IUserDatabase databaseStub = new UserDatabaseStub();
        this.dataHandler = new UserDataHandler(databaseStub);
    }
    @After
    public void tearDown() throws Exception {
        this.dataHandler=null;
    }
    @Test
    public void testGetCurrentUser(){
        dataHandler.currentUser = new User("Duy","duy.than@gihot.com","123456789", UserType.Employee);
        IUser curUser = dataHandler.getCurrentUser();
        assertEquals("name should be Duy","Duy",curUser.getRealName());
        assertEquals("email should be duy.than@gihot.com","duy.than@gihot.com",curUser.getUserID());
        assertEquals("password should be 123456789","123456789",curUser.getPassword());
        assertEquals("usertype should be employee",UserType.Employee,curUser.getUserType());
    }

    //without throwing exception
    @Test
    public void testLogIn() {
        try{
            dataHandler.logIn("duy.than@gihot.com","123456789");
        }
        catch (UserNotFoundException exception){
            assertEquals("it shouldn't reach the catch statement",exception.getMessage());
        }
        catch (DifferentPasswordException exception){
            assertEquals("it shouldn't reach the catch statement",exception.getMessage());
        }

    }
    //throw exception for id not found
    @Test
    public void testLogInThrowUserNotFoundException(){
        try{
            dataHandler.logIn("mybad@outlook.com","111");
        }
        catch (UserNotFoundException exception){
            assertEquals("User Not Found",exception.getMessage());
        }
    }
    //throw exception for password not correct
    @Test
    public void testLogInThrowDifferentPasswordException(){
        try{
            dataHandler.logIn("duy.than@gihot.com","123456789");
        }
        catch (DifferentPasswordException exception){
            assertEquals("Different passwords, couldn't confirm!!",exception.getMessage());
        }
    }
    @Test
    public void testLogOut() {
        dataHandler.logOut();
        assertNull("the current User should be null",dataHandler.getCurrentUser());
    }
    @Test
    public void testChangePasswordUserNullException() {
        //ChangePasswordException
        try{
            dataHandler.logOut();
            dataHandler.changePassword("123456789","11111111","11111111");
        }
        catch (ChangePasswordException exception){
            assertEquals("User must be logged in",exception.getMessage());
        }
    }
    @Test
    public void testChangePasswordWrongOldPasswordException(){
        IUser user = new User("Daniel","duy.than@gihot.com","111", UserType.Employee);
        dataHandler.currentUser = user;
        try{
            dataHandler.changePassword("222","111111","111111");
        }
        catch (ChangePasswordException exception){
            assertEquals("The password input doesn't match the saved password",exception.getMessage());
        }
    }
    @Test
    public void testChangePasswordShortPasswordException(){
        IUser user = new User("Daniel","duy.than@gihot.com","111", UserType.Employee);
        dataHandler.currentUser = user;
        try{
            dataHandler.changePassword("111","1234567","1234567");
        }
        catch (ChangePasswordException exception){
            assertEquals("Password length too short, should be at least 8 characters",exception.getMessage());
        }
    }
    @Test
    public void testChangePasswordNotMatchNewException(){
        IUser user = new User("Daniel","duy.than@gihot.com","111", UserType.Employee);
        dataHandler.currentUser = user;
        try{
            dataHandler.changePassword("111","123456789","1234567");
        }
        catch (ChangePasswordException exception){
            assertEquals("Different new passwords, couldn't confirm!!",exception.getMessage());
        }
    }
    @Test
    public void testChangePassword(){
        try{
            IUser user = new User("Daniel","duy.than@gihot.com","111", UserType.Employee);
            dataHandler.currentUser = user;
            boolean success = dataHandler.changePassword("111","123456789","123456789");
            assertEquals("It should change password successfully",true,success);
        }
        catch (ChangePasswordException exception){
            assertEquals("it shouldn't reach the catch statement",exception.getMessage());
        }

    }
    @Test
    public void testCreateNewUserNameException() {
        try{
            IUser user = dataHandler.createNewUser("","zigs@gmail.com","123456789",false);
        }
        catch (CreateUserErrorException exception){
            assertEquals("Name cannot be empty",exception.getMessage().trim());
        }
    }
    @Test
    public void testCreateNewUserEmail_1_Exception() {
        try{
            IUser user = dataHandler.createNewUser("Duy","","123456789",false);
        }
        catch (CreateUserErrorException exception){
            assertEquals("Email cannot be empty",exception.getMessage().trim());
        }
    }
    @Test
    public void testCreateNewUserEmail_2_Exception() {
        try{
            IUser user = dataHandler.createNewUser("Duy","zigszigs","123456789",false);
        }
        catch (CreateUserErrorException exception){
            assertEquals("Email is not valid",exception.getMessage().trim());
        }
    }
    @Test
    public void testCreateNewUserPassword_1_Exception() {
        try{
            IUser user = dataHandler.createNewUser("Duy","zigs123321@gmail.com","",false);
            System.out.println("uyes");
        }
        catch (CreateUserErrorException exception){
            assertEquals("Password cannot be empty",exception.getMessage().trim());
        }
    }
    @Test
    public void testCreateNewUserPassword_2_Exception() {
        try{
            IUser user = dataHandler.createNewUser("Duy","zigs123321@gmail.com","1234567",false);
            System.out.println("uyes");
        }
        catch (CreateUserErrorException exception){
            assertEquals("Password cannot be shorter than 8 characters",exception.getMessage().trim());
        }
    }
    @Test
    public void testCreateNewUser() {
        try{
            IUser user = dataHandler.createNewUser("Duy","zigs123321@gmail.com","123456789",false);
        }
        catch (CreateUserErrorException exception){
            assertEquals("It shouldn't reach the catch statement",exception.getMessage().trim());
        }
    }

}
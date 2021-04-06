package com.comp3350_group10.bookstore.business;

import android.content.Context;
import android.view.Gravity;

import android.widget.Toast;

import com.comp3350_group10.bookstore.Exceptions.CreateUserErrorException;
import com.comp3350_group10.bookstore.application.Main;
import com.comp3350_group10.bookstore.application.Service;
import com.comp3350_group10.bookstore.persistence.fakeDB.FakeUserDatabase;
import com.comp3350_group10.bookstore.presentation.Messages;
import com.comp3350_group10.bookstore.presentation.UI_Handler.ErrorHandler;
import com.comp3350_group10.bookstore.presentation.UI_Handler.IErrorHandler;
import com.comp3350_group10.bookstore.objects.User;
import com.comp3350_group10.bookstore.persistence.IUser;
import com.comp3350_group10.bookstore.persistence.IUserDatabase;
import com.comp3350_group10.bookstore.persistence.UserType;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.*;


public class UserDataHandler implements IUserDataHandler {

    public static IUser currentUser = null;
    private IUserDatabase userDatabase;
    private ErrorHandler errorHandler=new ErrorHandler();

    //Default constructor that calls on Service method to connect to database
    public UserDataHandler() {
        userDatabase = Service.setupUserDatabase();
//        userDatabase = new FakeUserDatabase();
    }

    public UserDataHandler(User currentUser) {
        UserDataHandler.currentUser = currentUser;
    }

    //function to check whether the current user is a manager or employee
    public boolean isCurrentUserManager(){
        return (UserType.Manager == currentUser.getUserType());
    }

    public IUser getCurrentUser() {
        return currentUser;
    }

    //function to login the current user
    public void logIn(String email, String password,Context context) {
        IUser tempUser = userDatabase.findUser(email);
        try{
            if(tempUser == null) throw new Exception(errorHandler.userNotFound());
            else if(!tempUser.getPassword().equals(password)) throw new Exception(errorHandler.differentPasswords());
            else {
                currentUser = tempUser;
                Messages.viewPopUp("Login successful",context);
            }
        }
        catch (Exception f) {
            String result=exceptionToString(f);
            System.out.println(f);
            Messages.viewPopUp(result,context);
        }
    }


    //function to logout the current user
    public void logOut(){
        if(currentUser!=null)
            currentUser = null;
    }

    //function to change password for the current logged in user
    public boolean changePassword(String oldPw, String newPw, String confirmNewPw){
        try {
            //check if the user is logged in or not
            if(currentUser == null){
                //IErrorHandler.ShowLoginErrorMessage("There is no currently logged in user");
                throw new Exception("User must be logged in");
            }
            else {
                try {
                    //check if the current password matches the old password
                    if(!currentUser.getPassword().equals(oldPw)){
                        throw new Exception("Current password doesn't match the saved password");
                    }
                    else {
                        try{
                            //check if the new password length is at least 8 characters (validation)
                            if(newPw.length()<8) {
                                throw new Exception("Password length too short, should be at least 8 characters");
                            }
                            else {
                                try{
                                    //check if the new password is confirmed or not
                                    if(!newPw.equals(confirmNewPw)){
                                        throw new Exception("Different passwords, couldn't confirm!!");
                                    }
                                    else {
                                        //if everything is correct, then update the password
                                        currentUser.setPassword(newPw);
                                        userDatabase.updateUser(currentUser);
                                        return true;
                                    }
                                }
                                catch (Exception g){
                                    System.out.println(g);
                                    return false;
                                }
                            }
                        }
                        catch (Exception f){
                            System.out.println(f);
                            return false;
                        }
                    }
                }
                catch (Exception e){
                    System.out.println(e);
                    return false;
                }

            }
        }
        catch (Exception h){
            System.out.println(h);
            return false;
        }
    }

    //creates a user and insert it into the database
    public IUser createNewUser(String name, String email, String password, boolean isManager) throws CreateUserErrorException{
        IUser newUser = null;
        UserType userType;

        //Check if input were valid
        //Add corresponding error message to print at the end
        String errorMessage = "";
        if(name.isEmpty())
            errorMessage += "Name cannot be empty\n";
        if(email.isEmpty())
            errorMessage += "Email cannot be empty\n";
        if(!validEmail(email))
            errorMessage += "Email is not valid\n";
        if(password.isEmpty())
            errorMessage += "Password cannot be empty\n";
        if(password.length() < 8)
            errorMessage += "Password cannot be shorter than 8 characters\n";

        //perform the insert if input was correct
        if(errorMessage.isEmpty())
        {
            userType = isManager ? UserType.Manager:UserType.Employee;

            newUser = new User(name, email, password, userType);
            userDatabase.insertUser(newUser);
        }
        else{ throw new CreateUserErrorException(errorMessage); }

        return newUser;
    }

    //Email validating method copied online
    private boolean validEmail(String email){
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    private String exceptionToString(Exception e){
        String exception=e.toString();
        return exception.substring(exception.lastIndexOf(":")+1);
    }


}

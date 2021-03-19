package com.comp3350_group10.bookstore.business.Data_Handler;

import com.comp3350_group10.bookstore.application.Main;
import com.comp3350_group10.bookstore.business.UI_Handler.ErrorHandler;
import com.comp3350_group10.bookstore.business.UI_Handler.IErrorHandler;
import com.comp3350_group10.bookstore.persistence.IUser;
import com.comp3350_group10.bookstore.persistence.IUserDatabase;
import com.comp3350_group10.bookstore.persistence.UserType;
import com.comp3350_group10.bookstore.persistence.hsqldb.UserDatabase;


public class UserDataHandler implements IUserDataHandler {

    public static IUser currentUser;
    private final IUserDatabase userDatabase;

    ////////////////////////CONSTRUCTORS/////////////////////////////////////////////
    //regular
    public UserDataHandler(){
         currentUser = null;
         userDatabase = new UserDatabase(Main.getDBPath());
    }

    //for testing (fake database injection)
    public UserDataHandler(IUserDatabase db, IUser user){
        this.userDatabase = db;
        currentUser = user;
    }


    /////////////////////////FUNCTIONS////////////////////////////////////////
    //function to check whether the current user is a manager or employee
    public boolean isCurrentUserManager(){
        boolean result = false;
        try{
         result = (UserType.Manager == currentUser.getUserType());
        }
        catch(NullPointerException e){
            System.out.println("not logged in");
        }
        return result;
    }

    //function to login the current user
    public void logIn(String email, String password) throws ClassNotFoundException {

        IUser tempUser = userDatabase.findUser(email);

        try{
            //check if the user is in the database or not
            if(tempUser == null) {
                throw new Exception("User does not exist");
            }
            else {
                try{
                    //check if the given password matches the tempUser's password
                    if(!tempUser.getPassword().equals(password)){
                        throw new Exception("Different passwords, couldn't confirm!!");
                    }
                    else {
                        //if password matches, then update the currentUser
                        currentUser = tempUser;
                    }
                }
                catch (Exception g){
                    System.out.println(g);
                }
            }
        }
        catch (Exception f){
            System.out.println(f);
        }
    }

    //function to logout the current user
    public void logOut(){
        if(currentUser!=null)
            currentUser = null;
    }

    //function to change password for the current logged in user
    public void changePassword(String oldPw, String newPw, String confirmNewPw){
        try {
            //check if the user is logged in or not
            if(currentUser == null){
                IErrorHandler.ShowLoginErrorMessage("There is no currently logged in user");
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
                                    }
                                }
                                catch (Exception g){
                                    System.out.println(g);
                                }
                            }
                        }
                        catch (Exception f){
                            System.out.println(f);
                        }
                    }
                }
                catch (Exception e){
                    System.out.println(e);
                }

            }
        }
        catch (Exception h){
            System.out.println(h);
        }
    }


    // TODO: Take care of IUserDatabase bugs after it's implemented
}

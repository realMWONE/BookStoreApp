package com.comp3350_group10.bookstore.business;

import com.comp3350_group10.bookstore.application.Main;
import com.comp3350_group10.bookstore.application.Service;
import com.comp3350_group10.bookstore.persistence.fakeDB.FakeUserDatabase;
import com.comp3350_group10.bookstore.presentation.UI_Handler.IErrorHandler;
import com.comp3350_group10.bookstore.objects.User;
import com.comp3350_group10.bookstore.persistence.IUser;
import com.comp3350_group10.bookstore.persistence.IUserDatabase;
import com.comp3350_group10.bookstore.persistence.UserType;
import com.comp3350_group10.bookstore.persistence.hsqldb.UserDatabase;


public class UserDataHandler implements IUserDataHandler {

    public static IUser currentUser = null;
    private IUserDatabase userDatabase = new UserDatabase(Main.getDBPath());

    //Default constructor that calls on Service method to connect to database
    public UserDataHandler() {
        //userDatabase = Service.setupUserDatabase();
        userDatabase = new FakeUserDatabase();
    }


    //function to check whether the current user is a manager or employee
    public boolean isCurrentUserManager(){
        return (UserType.Manager == currentUser.getUserType());
    }

    //function to login the current user
    public void logIn(String email, String password) throws ClassNotFoundException {

        IUser tempUser = userDatabase.findUser(email);

        try{
            //check if the user is in the database or not
            if(tempUser == null) {
                throw new Exception("User doesn't exist");
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

    //creates a user and insert it into the database
    public IUser createNewUser(String name, String email, String password, boolean isManager) throws ClassNotFoundException {
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

        //TODO: popup displaying the message

        return newUser;
    }

    //Email validating method copied online
    private boolean validEmail(String email){
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

}

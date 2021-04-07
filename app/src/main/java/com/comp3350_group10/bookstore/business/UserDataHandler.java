package com.comp3350_group10.bookstore.business;

import com.comp3350_group10.bookstore.Exceptions.ChangePasswordException;
import com.comp3350_group10.bookstore.Exceptions.CreateUserErrorException;
import com.comp3350_group10.bookstore.Exceptions.DifferentPasswordException;
import com.comp3350_group10.bookstore.Exceptions.UserNotFoundException;
import com.comp3350_group10.bookstore.application.Service;
import com.comp3350_group10.bookstore.persistence.fakeDB.FakeUserDatabase;
import com.comp3350_group10.bookstore.presentation.UI_Handler.ErrorHandler;
import com.comp3350_group10.bookstore.objects.User;
import com.comp3350_group10.bookstore.persistence.IUser;
import com.comp3350_group10.bookstore.persistence.IUserDatabase;
import com.comp3350_group10.bookstore.persistence.UserType;


public class UserDataHandler implements IUserDataHandler {

    public static IUser currentUser = null;
    private IUserDatabase userDatabase;

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
    public void logIn(String email, String password){
        IUser tempUser = userDatabase.findUser(email);
            if(tempUser == null) throw new UserNotFoundException("User Not Found");
            else if(!tempUser.getPassword().equals(password)) throw new DifferentPasswordException("Different passwords, couldn't confirm!!");
            else {
                currentUser = tempUser;
            }
    }


    //function to logout the current user
    public void logOut(){
        if(currentUser!=null)
            currentUser = null;
    }

    //function to change password for the current logged in user
    public boolean changePassword(String oldPw, String newPw, String confirmNewPw){
        //check if the user is logged in or not
        IUser temp;
        if(currentUser == null) throw new ChangePasswordException("User must be logged in");
            //check if the current password matches the old password
        else if (!currentUser.getPassword().equals(oldPw)) throw new ChangePasswordException("Current password doesn't match the saved password");
            //check if the new password length is at least 8 characters (validation)
        else if (newPw.length() < 8) throw new ChangePasswordException("Password length too short, should be at least 8 characters");
            //check if the new password is confirmed or not
        else if (!newPw.equals(confirmNewPw)) throw new ChangePasswordException("Different new passwords, couldn't confirm!!");
        else {
            //if everything is correct, then update the password
            currentUser.setPassword(newPw);
            temp=userDatabase.updateUser(currentUser);
            return temp != null;
        }
    }

    //creates a user and insert it into the database
    public IUser createNewUser(String name, String email, String password, boolean isManager) throws CreateUserErrorException{
        IUser newUser;
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
}

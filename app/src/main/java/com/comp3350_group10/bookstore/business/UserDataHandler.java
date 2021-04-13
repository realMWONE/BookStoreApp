package com.comp3350_group10.bookstore.business;

import com.comp3350_group10.bookstore.Exceptions.*;
import com.comp3350_group10.bookstore.application.Service;
import com.comp3350_group10.bookstore.objects.User;
import com.comp3350_group10.bookstore.persistence.IUser;
import com.comp3350_group10.bookstore.persistence.IUserDatabase;
import com.comp3350_group10.bookstore.persistence.UserType;
import com.comp3350_group10.bookstore.presentation.Messages;


public class UserDataHandler implements IUserDataHandler {

    public static IUser currentUser = null;
    private IUserDatabase userDatabase;

    //Default constructor that calls on Service method to connect to database
    public UserDataHandler() {
        userDatabase = Service.setupUserDatabase();
    }
    public UserDataHandler(IUserDatabase userDatabase){
        this.userDatabase=userDatabase;
    }


    public UserDataHandler(IUser currentUser) {
        UserDataHandler.currentUser = currentUser;
    }

    public IUser getCurrentUser() {
        return currentUser;
    }

    //function to login the current user
    public void logIn(String email, String password) throws UserNotFoundException, DifferentPasswordException{
        IUser tempUser = userDatabase.findUser(email);
        if(tempUser == null) throw new UserNotFoundException("User Not Found");
        else if(!tempUser.getPassword().equals(password)) throw new DifferentPasswordException("Different passwords, couldn't confirm!!");
        else {
            currentUser = tempUser;
        }
    }

    public void ForgotPassword(String email) throws UserNotFoundException{
        IUser tempUser = userDatabase.findUser(email);
        if (tempUser == null) throw new UserNotFoundException("User Not Found");
        else {
            tempUser.setPassword("12345678");
            userDatabase.updateUser(tempUser);
        }
    }

    //function to logout the current user
    public void logOut(){
        if(currentUser!=null)
            currentUser = null;
    }

    //function to change password for the current logged in user
    public boolean changePassword(String oldPw, String newPw, String confirmNewPw) throws ChangePasswordException{
        //check if the user is logged in or not
        IUser temp;
        if(currentUser == null) throw new ChangePasswordException("User must be logged in");

        //check if the current password matches the old password
        else if (!currentUser.getPassword().equals(oldPw)) throw new ChangePasswordException("The password input doesn't match the saved password");

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

        //name check
        if(name.isEmpty())
            errorMessage += "Name cannot be empty";

        //formatting: newline if errorMessage is not empty
        if(!errorMessage.isEmpty())
            errorMessage += "\n";

        //email check, only one should show
        if (email.isEmpty())
            errorMessage += "Email cannot be empty";
        else if (!validEmail(email))
            errorMessage += "Email is not valid";

        //formatting: newline if errorMessage isn't empty
        if(!errorMessage.isEmpty())
            errorMessage += "\n";

        //password checks, only one should show
        if(password.isEmpty())
            errorMessage += "Password cannot be empty";
        else if(password.length() < 8)
            errorMessage += "Password cannot be shorter than 8 characters";

        //perform the insert if input was correct
        if(errorMessage.isEmpty())
        {
            userType = isManager ? UserType.Manager:UserType.Employee;

            newUser = new User(name, email, password, userType);

            try {
                userDatabase.insertUser(newUser);
            }
            catch(PersistenceException e){
                throw new CreateUserErrorException("Creation failed. Perhaps the email is already used?");
            }

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

    //Delete the user with the given ID from database
    public void deleteUser(String deleteID) throws PersistenceException, UserNotFoundException, DeleteLoggedInUserException{
        if(deleteID.toLowerCase().equals(currentUser.getUserID().toLowerCase())){
            throw new DeleteLoggedInUserException("You cannot delete your own account.");
        }
        else {
            IUser u = userDatabase.findUser(deleteID);
            if (u != null) {
                userDatabase.deleteUser(u);
            } else {
                throw new UserNotFoundException("User does not exist");
            }
        }
    }
}

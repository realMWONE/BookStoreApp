package com.comp3350_group10.bookstore.business.Data_Handler;

import com.comp3350_group10.bookstore.objects.User;
import com.comp3350_group10.bookstore.persistence.UserType;
import com.comp3350_group10.bookstore.persistence.hsqldb.UserDatabase;

import java.util.ArrayList;
import java.util.List;


public class UserDataHandler implements IUserDataHandler {

    public static User currentUser = null;
    private UserDatabase userDatabase = new UserDatabase();

    public UserDataHandler(){}

    public UserDataHandler(User currentUser){
        this.currentUser=currentUser;
    }


    //function to check whether the current user is a manager or employee
    public boolean isCurrentUserManager(){
        return (UserType.Manager == currentUser.getUserType());
    }

    //function to login the current user
    public void logIn(String email, String password){
//
//        User tempUser = userDatabase.searchUser(email);
//
//        try{
//            //check if the user is in the database or not
//            if(tempUser == null) {
//                throw new Exception("Password length too short, should be at least 8 characters");
//            }
//            else {
//                try{
//                    //check if the given password matches the tempUser's password
//                    if(!tempUser.getPassword().equals(password)){
//                        throw new Exception("Different passwords, couldn't confirm!!");
//                    }
//                    else {
//                        //if password matches, then update the currentUser
//                        currentUser = tempUser;
//                    }
//                }
//                catch (Exception g){
//                    System.out.println(g);
//                }
//            }
//        }
//        catch (Exception f){
//            System.out.println(f);
//        }
    }

    //function to logout the current user
    public void logOut(){
        if(currentUser!=null)
            currentUser = null;
    }

    // splits the given string, ignores non-ascii words
    private List<String> splitWords(String words){
        //split input
        String[] split = words.toLowerCase().split("[-. ,:]+");

        //initialize returning list
        List<String> result = new ArrayList<>();

        //ignore non-ascii and common words
        for(String word:split) {
            if(word.matches("\\A\\p{ASCII}*\\z")){
                result.add(word);
            }
        }

        return result;
    }

    //function to change password for the current logged in user
    public void changePassword(String oldPw, String newPw, String confirmNewPw){
        try {
            //check if the user is logged in or not
            if(currentUser == null){
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

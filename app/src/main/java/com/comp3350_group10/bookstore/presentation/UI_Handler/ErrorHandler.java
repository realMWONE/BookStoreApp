package com.comp3350_group10.bookstore.presentation.UI_Handler;

public class ErrorHandler implements IErrorHandler{

    @Override
    public String userNotFound() {
        return "User Not Found";
    }

    public String differentPasswords(){
        return "Different passwords, couldn't confirm!!";
    }
}

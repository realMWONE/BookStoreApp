package com.comp3350_group10.bookstore.presentation.login;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.comp3350_group10.bookstore.persistence.fakeDB.LoginDataSource;
import com.comp3350_group10.bookstore.persistence.fakeDB.LoginRepository;


/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
public class LoginViewModelFactory implements ViewModelProvider.Factory
{

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass)
    {
        if (modelClass.isAssignableFrom(LoginViewModel.class))
        {
            return (T) new LoginViewModel(LoginRepository.getInstance(new LoginDataSource()));
        } else
        {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
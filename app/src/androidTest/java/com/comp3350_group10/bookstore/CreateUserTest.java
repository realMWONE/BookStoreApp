package com.comp3350_group10.bookstore;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.comp3350_group10.bookstore.business.UserDataHandler;
import com.comp3350_group10.bookstore.objects.User;
import com.comp3350_group10.bookstore.persistence.UserType;
import com.comp3350_group10.bookstore.persistence.hsqldb.BookDatabase;
import com.comp3350_group10.bookstore.presentation.CreateUserActivity;
import com.comp3350_group10.bookstore.presentation.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CreateUserTest {
    UserDataHandler uHandler;
    @Rule
    public ActivityScenarioRule<MainActivity> activityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setup()
    {
        uHandler = new UserDataHandler();
        UserDataHandler.currentUser = new User("Manage Chan","manager@manager.com","ILoveManagement", UserType.Manager);
        //have to disable Window animation scale, Transition scale, Animator duration scale from emulator Dev options setting
    }

    @Test
    public void createUser() {
        Activity a = GetActivity.getActivity(activityTestRule);
        a.startActivity(new Intent(a.getBaseContext(), CreateUserActivity.class));

        onView(withId(R.id.new_name_text)).perform(typeText("New User"));
        onView(withId(R.id.new_email_text)).perform(typeText("newuser@mail.com"));
        onView(withId(R.id.new_password_text)).perform(typeText("12345678"));

        pressBack();
        onView(withId(R.id.create_user_button)).perform(click());

        a.startActivity(new Intent(a.getBaseContext(), MainActivity.class));

        uHandler.logIn("newuser@mail.com","12345678");
        assertEquals("Failed: Not able to log in as the new created user.",UserDataHandler.currentUser.getRealName(), "New User");
    }
}
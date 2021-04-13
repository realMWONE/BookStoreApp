package com.comp3350_group10.bookstore;

import android.app.Activity;
import android.content.Intent;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.comp3350_group10.bookstore.TestHelper.GetActivity;
import com.comp3350_group10.bookstore.business.UserDataHandler;
import com.comp3350_group10.bookstore.presentation.LoginActivity;
import com.comp3350_group10.bookstore.presentation.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.comp3350_group10.bookstore.business.UserDataHandler.currentUser;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LogInTest {
    String testUser, password;
    UserDataHandler uHandler;

    @Rule
    public ActivityScenarioRule<MainActivity> activityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setup(){
         testUser = "test@test.ca";
         password = "222222222";
         uHandler = new UserDataHandler();
         uHandler.createNewUser("test",testUser,password,false);
        //make sure user not already in db
        try{
            uHandler.deleteUser("newuser@mail.com");
        }
        catch(Exception e){}
    }

    @After
    public void tearDown(){
        currentUser = null;
        uHandler.deleteUser(testUser);
    }

    @Test
    public void login() {
        Activity a = GetActivity.getActivity(activityTestRule);
        a.startActivity(new Intent(a.getBaseContext(), LoginActivity.class));


        onView(withId(R.id.username)).perform(typeText(testUser));
        onView(withId(R.id.password)).perform(typeText(password));
        onView(withId(R.id.loginButton)).perform(click());

        assertEquals(testUser.toLowerCase(), currentUser.getUserID().toLowerCase());
    }
}
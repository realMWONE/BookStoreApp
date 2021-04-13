package com.comp3350_group10.bookstore;

import android.app.Activity;
import android.content.Intent;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.comp3350_group10.bookstore.TestUtil.GetActivity;
import com.comp3350_group10.bookstore.business.UserDataHandler;
import com.comp3350_group10.bookstore.objects.User;
import com.comp3350_group10.bookstore.persistence.UserType;
import com.comp3350_group10.bookstore.presentation.CreateUserActivity;
import com.comp3350_group10.bookstore.presentation.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.comp3350_group10.bookstore.business.UserDataHandler.currentUser;
import static org.junit.Assert.assertEquals;

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

        //Have to manually disable Window animation scale, Transition scale, Animator duration scale from emulator Dev options setting


        //make sure user not already in db
        try{
            uHandler.deleteUser("newuser@mail.com");
        }
        catch(Exception e){}
    }

    @After
    public void tearDown(){
        currentUser = null;
        uHandler.deleteUser("newuser@mail.com");
    }

    @Test
    public void createUser() {
        Activity a = GetActivity.getActivity(activityTestRule);
        a.startActivity(new Intent(a.getBaseContext(), CreateUserActivity.class));

        onView(withId(R.id.new_name_text)).perform(typeText("New User"));
        pressBack();
        onView(withId(R.id.new_email_text)).perform(typeText("newuser@mail.com"));
        pressBack();
        onView(withId(R.id.new_password_text)).perform(typeText("12345678"));

        closeSoftKeyboard();
        onView(withId(R.id.create_user_button)).perform(click());

        //Test
        uHandler.logIn("newuser@mail.com","12345678");
        assertEquals("Failed: Not able to log in as the new created user.",UserDataHandler.currentUser.getRealName(), "New User");
    }
}
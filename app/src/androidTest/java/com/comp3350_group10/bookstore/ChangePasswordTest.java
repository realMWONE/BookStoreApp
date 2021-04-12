package com.comp3350_group10.bookstore;

import android.app.Activity;
import android.content.Intent;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.comp3350_group10.bookstore.Exceptions.PersistenceException;
import com.comp3350_group10.bookstore.business.UserDataHandler;
import com.comp3350_group10.bookstore.objects.User;
import com.comp3350_group10.bookstore.persistence.IUser;
import com.comp3350_group10.bookstore.persistence.UserType;
import com.comp3350_group10.bookstore.presentation.MainActivity;
import com.comp3350_group10.bookstore.presentation.UserSettingActivity;

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
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ChangePasswordTest {
    UserDataHandler uHandler;
    @Rule
    public ActivityScenarioRule<MainActivity> activityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setup()
    {
        uHandler = new UserDataHandler();
        //Have to manually disable Window animation scale, Transition scale, Animator duration scale from emulator Dev options setting
        UserDataHandler.currentUser = uHandler.createNewUser("Manage Chan","manager@manager.com","ILoveManagement", true);
    }

    @Test
    public void changePassword() {
        Activity a = GetActivity.getActivity(activityTestRule);
        a.startActivity(new Intent(a.getBaseContext(), UserSettingActivity.class));

        onView(withId(R.id.oldPassword)).perform(typeText("ILoveManagement"));
        pressBack();
        onView(withId(R.id.newPassword)).perform(typeText("12345678"));
        pressBack();
        onView(withId(R.id.confirmNewPassword)).perform(typeText("12345678"));

        closeSoftKeyboard();
        onView(withId(R.id.user_change_password)).perform(click());
//        a.startActivity(new Intent(a.getBaseContext(), MainActivity.class));

        //Test
        uHandler.logIn("manager@manager.com","12345678");
        assertEquals("Failed: Not able to log in as the new created user.",UserDataHandler.currentUser.getRealName(), "Manage Chan");

//        uHandler.deleteUser("manager@manager.com");
    }
}
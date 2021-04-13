package com.comp3350_group10.bookstore.TestUtil;

import android.app.Activity;
import android.os.Looper;
import android.view.Menu;

import androidx.appcompat.view.menu.MenuBuilder;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.comp3350_group10.bookstore.R;
import com.comp3350_group10.bookstore.business.UserDataHandler;
import com.comp3350_group10.bookstore.presentation.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.comp3350_group10.bookstore.business.UserDataHandler.currentUser;

public class LoginHelper {
    public static String testUser, password;
    public static UserDataHandler uHandler;

    public static void setupNewUser() {
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

    public static void deleteNewUser() {
        currentUser = null;
        uHandler.deleteUser(testUser);
    }

    public static void login(ActivityScenarioRule<MainActivity> activityTestRule) {
        Looper.prepare();
        Activity a = GetActivity.getActivity(activityTestRule);
        Menu menu = new MenuBuilder(a);
        a.getMenuInflater().inflate(R.menu.main, menu);
        menu.performIdentifierAction(R.id.login_button, 0);

        onView(withId(R.id.username)).perform(typeText(testUser));
        onView(withId(R.id.password)).perform(typeText(password));
        onView(withId(R.id.loginButton)).perform(click());
    }
}

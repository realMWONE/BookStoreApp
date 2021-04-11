package com.comp3350_group10.bookstore;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.comp3350_group10.bookstore.application.Main;
import com.comp3350_group10.bookstore.presentation.LoginActivity;
import com.comp3350_group10.bookstore.presentation.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.comp3350_group10.bookstore.business.UserDataHandler.currentUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LogInTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setup(){}

    @Test
    public void login() {
        Activity a = GetActivity.getActivity(activityTestRule);
        a.startActivity(new Intent(a.getBaseContext(), LoginActivity.class));

        String testUser = "matt@myumanitoba.ca";
        String password = "222222222";
        onView(withId(R.id.username)).perform(typeText(testUser));
        onView(withId(R.id.password)).perform(typeText(password));
        onView(withId(R.id.loginButton)).perform(click());

        assertEquals(testUser.toLowerCase(), currentUser.getUserID().toLowerCase());
    }
}
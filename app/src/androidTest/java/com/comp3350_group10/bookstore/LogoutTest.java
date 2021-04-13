package com.comp3350_group10.bookstore;

import android.app.Activity;
import android.content.Intent;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;

import androidx.appcompat.view.menu.MenuBuilder;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.comp3350_group10.bookstore.TestUtil.GetActivity;
import com.comp3350_group10.bookstore.TestUtil.LoginHelper;
import com.comp3350_group10.bookstore.business.UserDataHandler;
import com.comp3350_group10.bookstore.objects.User;
import com.comp3350_group10.bookstore.persistence.UserType;
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
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LogoutTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setup(){
        LoginHelper.setupNewUser();
    }

    @After
    public void tearDown(){
        LoginHelper.deleteNewUser();
    }

    @Test
    public void testLogout() {
        Activity a = GetActivity.getActivity(activityTestRule);
        LoginHelper.login(activityTestRule);

        Menu menu = new MenuBuilder(a);
        a.getMenuInflater().inflate(R.menu.main, menu);
        menu.performIdentifierAction(R.id.logout_button, 0);

        assertTrue("Logout failed", currentUser == null);
    }
}

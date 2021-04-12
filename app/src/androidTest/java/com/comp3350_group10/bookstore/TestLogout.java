package com.comp3350_group10.bookstore;

import android.content.Intent;
import android.os.Looper;
import android.view.Menu;

import androidx.appcompat.view.menu.MenuBuilder;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.comp3350_group10.bookstore.objects.User;
import com.comp3350_group10.bookstore.persistence.IUser;
import com.comp3350_group10.bookstore.persistence.UserType;
import com.comp3350_group10.bookstore.presentation.LoginActivity;
import com.comp3350_group10.bookstore.presentation.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.comp3350_group10.bookstore.business.UserDataHandler.currentUser;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestLogout {
    @Rule
    public ActivityScenarioRule<MainActivity> activityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testLogout() {
        Looper.prepare();
        MainActivity a = GetActivity.getActivity(activityTestRule);
        a.startActivity(new Intent(a.getBaseContext(), LoginActivity.class));

        IUser user = new User("Mr. Test","testUser@gmail.com", "password", UserType.Employee);
        currentUser = user;

        Menu menu = new MenuBuilder(a);
        a.getMenuInflater().inflate(R.menu.main, menu);
        menu.performIdentifierAction(R.id.logout_button, 0);

        assertTrue("Logout failed", currentUser == null);
    }
}

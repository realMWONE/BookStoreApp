package com.comp3350_group10.bookstore;

import android.app.Activity;
import android.content.Intent;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.comp3350_group10.bookstore.TestUtil.TestHelper;
import com.comp3350_group10.bookstore.business.BookDataHandler;
import com.comp3350_group10.bookstore.business.IBookDataHandler;
import com.comp3350_group10.bookstore.business.UserDataHandler;
import com.comp3350_group10.bookstore.objects.IBook;
import com.comp3350_group10.bookstore.presentation.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PriceChangeTest
{
    UserDataHandler uHandler;

    @Rule
    public ActivityScenarioRule<MainActivity> activityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setup(){
        TestHelper.setupHelper();
        uHandler = new UserDataHandler();
        uHandler.logIn(TestHelper.adminID, TestHelper.adminPw);
    }

    @After
    public void tearDown(){
        TestHelper.tearDownHelper();
    }

    @Test
    public void changeStock() {
        Activity activity = TestHelper.getActivity(activityTestRule);
        activity.startActivity(new Intent(activity, MainActivity.class));

        onView(withId(R.id.searchBar)).perform(click());
        onView(withId(R.id.searchBar)).perform(typeText("Harry Potter and the Chamber of Secrets"));

        TableLayout table = activity.findViewById(R.id.bookListTable);
        TableRow row = (TableRow) table.getChildAt(0);
        row.callOnClick();

        onView(withId(R.id.change_price_text)).perform(click());
        onView(withId(R.id.change_price_text)).perform(typeText("100"));
        closeSoftKeyboard();
        onView(withId(R.id.set_price_button)).perform(click());

        IBookDataHandler bookHandler = new BookDataHandler();
        IBook book = bookHandler.findBooks("Harry Potter and the Chamber of Secrets", true, "Title").get(0);

        assertEquals("Setting the price with the manager controls has failed. New price: " + book.getPrice(), 10000, book.getPrice());  //database stores price in cents, $100 = 10000 cents
    }
}

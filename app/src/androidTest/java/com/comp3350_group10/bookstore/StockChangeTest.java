package com.comp3350_group10.bookstore;

import android.app.Activity;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.comp3350_group10.bookstore.TestHelper.GetActivity;
import com.comp3350_group10.bookstore.business.BookDataHandler;
import com.comp3350_group10.bookstore.business.IBookDataHandler;
import com.comp3350_group10.bookstore.business.UserDataHandler;
import com.comp3350_group10.bookstore.objects.User;
import com.comp3350_group10.bookstore.objects.IBook;
import com.comp3350_group10.bookstore.persistence.UserType;
import com.comp3350_group10.bookstore.presentation.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class StockChangeTest
{
    @Rule
    public ActivityScenarioRule<MainActivity> activityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setup(){
        UserDataHandler.currentUser = new User("Manage Chan","manager@manager.com","ILoveManagement", UserType.Manager);
    }

    @Test
    public void reduceStock() {
        onView(withId(R.id.searchBar)).perform(click());
        onView(withId(R.id.searchBar)).perform(typeText("Harry Potter and the Chamber of Secrets"));

        Activity activity = GetActivity.getActivity(activityTestRule);
        TableLayout table = activity.findViewById(R.id.bookListTable);
        TableRow row = (TableRow) table.getChildAt(0);
        row.callOnClick();

        int stockAmount = BookDataHandler.currentBook.getStock();

        onView(withId(R.id.bookDetailsSaleButton)).perform(click());

        IBookDataHandler bookHandler = new BookDataHandler();
        IBook book = bookHandler.findBooks("Harry Potter and the Chamber of Secrets", true, "Title").get(0);

        int newStockAmount = book.getStock();

        assertTrue("Reducing the stock with the Sale button has failed.",newStockAmount == stockAmount-1);
    }

    @Test
    public void changeStock() {
        onView(withId(R.id.searchBar)).perform(click());
        onView(withId(R.id.searchBar)).perform(typeText("Harry Potter and the Chamber of Secrets"));

        Activity activity = GetActivity.getActivity(activityTestRule);
        TableLayout table = activity.findViewById(R.id.bookListTable);
        TableRow row = (TableRow) table.getChildAt(0);
        row.callOnClick();

        onView(withId(R.id.change_stock_text)).perform(click());
        onView(withId(R.id.change_stock_text)).perform(typeText("100"));
        onView(withId(R.id.set_stock_button)).perform(click());

        IBookDataHandler bookHandler = new BookDataHandler();
        IBook book = bookHandler.findBooks("Harry Potter and the Chamber of Secrets", true, "Title").get(0);

        assertTrue("Setting the stock with the manager controls has failed.", book.getStock() == 100);
    }
}

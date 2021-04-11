package com.comp3350_group10.bookstore;

import android.app.Activity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.comp3350_group10.bookstore.application.Service;
import com.comp3350_group10.bookstore.persistence.IBookDatabase;
import com.comp3350_group10.bookstore.presentation.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.atomic.AtomicReference;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class BookSearchTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setupDatabase(){
        IBookDatabase bookDatabase = Service.setupBookDatabase();
    }

    @Test
    public void searchBooks(){
        onView(withId(R.id.searchBar)).perform(click());
        onView(withId(R.id.searchBar)).perform(typeText("Harry Potter and the Chamber of Secrets"));

        activityTestRule.getScenario().onActivity(activity ->
        {
            TableLayout table = activity.findViewById(R.id.bookListTable);
            TableRow row = (TableRow) table.getChildAt(0);
            TextView textView = (TextView)row.getChildAt(1);
            assertTrue("A search for Harry Potter and the Chamber of Secrets did not yield the correct results", textView.getText().toString().contains("Harry Potter and the Chamber of Secrets"));
        });
    }
}

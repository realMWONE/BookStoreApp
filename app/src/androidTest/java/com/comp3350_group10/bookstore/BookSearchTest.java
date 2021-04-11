package com.comp3350_group10.bookstore;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.comp3350_group10.bookstore.application.Service;
import com.comp3350_group10.bookstore.persistence.IBook;
import com.comp3350_group10.bookstore.persistence.IBookDatabase;
import com.comp3350_group10.bookstore.presentation.MainActivity;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

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
        onView(ViewMatchers.withId(R.id.searchBar)).perform(click());
        onView(ViewMatchers.withId(R.id.searchBar)).perform(typeText("Harry Potter and the Chamber of Secrets"));
        //onView(ViewMatchers.withId(R.id.bookListTable)).perform(click());
//        closeSoftKeyboard();
    }
}

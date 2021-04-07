package com.comp3350_group10.bookstore;

import com.comp3350_group10.bookstore.objects.BookTest;
import com.comp3350_group10.bookstore.objects.UserTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(
        Suite.class
)
@Suite.SuiteClasses(
        {BookTest.class,
        UserTest.class}

)
public class AllTests {
    //For the class LoggedInUserTest

}

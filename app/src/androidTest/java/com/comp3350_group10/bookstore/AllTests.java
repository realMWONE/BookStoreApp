package com.comp3350_group10.bookstore;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(
        Suite.class
)
@Suite.SuiteClasses(
        {
                BookSearchTest.class,
                ChangePasswordTest.class,
                CreateUserTest.class,
                LogInTest.class,
                SortTest.class,
                StockChangeTest.class,
                LogoutTest.class
        }

)
public class AllTests {
    //For the class LoggedInUserTest

}

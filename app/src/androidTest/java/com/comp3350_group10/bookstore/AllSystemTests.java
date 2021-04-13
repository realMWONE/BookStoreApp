package com.comp3350_group10.bookstore;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(
        Suite.class
)
@Suite.SuiteClasses(
        {
                LogInTest.class,
                BookSearchTest.class,
                ChangePasswordTest.class,
                CreateUserTest.class,
                SortTest.class,
                StockChangeTest.class,
                RemoveUserTest.class,
                ForgotPasswordTest.class,
                ContactUsTest.class
        }

)
public class AllSystemTests {
    //For the class LoggedInUserTest

}

package com.comp3350_group10.bookstore;

import com.comp3350_group10.bookstore.business.BookDataHandlerIT;
import com.comp3350_group10.bookstore.business.UserDataHandlerTestIT;
import com.comp3350_group10.bookstore.business.UserDatabaseIT;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(
        Suite.class
)
@Suite.SuiteClasses(
        {
                BookDataHandlerIT.class,
                UserDatabaseIT.class,
                UserDataHandlerTestIT.class
        }

)
public class AllIntegrationTests {
    //For the class LoggedInUserTest

}

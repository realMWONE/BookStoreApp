package com.comp3350_group10.bookstore;

import com.comp3350_group10.bookstore.application.ServiceTest;
import com.comp3350_group10.bookstore.business.BookDataHandlerIT;
import com.comp3350_group10.bookstore.business.BookDataHandlerTest;
import com.comp3350_group10.bookstore.business.UserDataHandlerIT;
import com.comp3350_group10.bookstore.business.UserDataHandlerTest;
import com.comp3350_group10.bookstore.objects.BookTest;
import com.comp3350_group10.bookstore.objects.UserTest;
import com.comp3350_group10.bookstore.persistence.hsqldb.ImageReferencesTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(
        Suite.class
)
@Suite.SuiteClasses(
        {
                AllIntegrationTests.class,
                AllUnitTests.class
        }

)
public class AllTests {
    //For the class LoggedInUserTest

}

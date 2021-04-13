package com.comp3350_group10.bookstore;

import com.comp3350_group10.bookstore.application.ServiceTest;
import com.comp3350_group10.bookstore.business.BookDataHandlerTest;
import com.comp3350_group10.bookstore.business.UserDataHandlerTest;
import com.comp3350_group10.bookstore.objects.BookTest;
import com.comp3350_group10.bookstore.objects.UserTest;
import com.comp3350_group10.bookstore.persistence.fakeDB.LoggedInUserTest;
import com.comp3350_group10.bookstore.persistence.fakeDB.LoginDataSourceTest;
import com.comp3350_group10.bookstore.persistence.hsqldb.ImageReferencesTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(
        Suite.class
)
@Suite.SuiteClasses(
        {
                ServiceTest.class,
                BookDataHandlerTest.class,
                UserDataHandlerTest.class,
                BookTest.class,
                UserTest.class,
                LoggedInUserTest.class,
                LoginDataSourceTest.class,
                ImageReferencesTest.class
        }

)
public class AllUnitTests {
    //For the class LoggedInUserTest

}
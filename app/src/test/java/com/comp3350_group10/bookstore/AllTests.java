package com.comp3350_group10.bookstore;

import com.comp3350_group10.bookstore.persistence.hsqldb.BookDatabaseTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(
        Suite.class
)
@Suite.SuiteClasses(
        {BookDatabaseTest.class}
)
public class AllTests {
    //For the class LoggedInUserTest

}

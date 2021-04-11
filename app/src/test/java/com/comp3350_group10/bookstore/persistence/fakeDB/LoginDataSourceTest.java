package com.comp3350_group10.bookstore.persistence.fakeDB;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginDataSourceTest extends TestCase {

    LoginDataSource source;
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.source = new LoginDataSource();
    }
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testLogin() {
        try{
            Result<LoggedInUser> result = source.login("duy.than@gihot.com","123456789");
            assertNotNull("This result should not be null",result);
        }
        catch (Exception ex){
            assertEquals("It should not reach this catch statement",ex.getMessage());
        }
    }
    //@Test
    /*public void testLoginException(){
        Result<LoggedInUser> result = source.login("","");
        assertNotNull("This result should not be null",result);
        boolean fail = result instanceof Result.Error;
        assertEquals("this should be true",true,fail);

    }*/

    @Test
    public void testLogout() {
    }
}
package com.comp3350_group10.bookstore.persistence.fakeDB;

import junit.framework.TestCase;

public class LoggedInUserTest extends TestCase {
    private LoggedInUser user;
    public void setUp() throws Exception {
        super.setUp();
        this.user = new LoggedInUser("duy.than@gihot.com","Daniel");
    }

    public void tearDown() throws Exception {
        this.user=null;
    }

    public void testGetUserId() {
        assertEquals("the user id should be duy.than@gihot.com","duy.than@gihot.com",user.getUserId());
    }

    public void testGetDisplayName() {
        assertEquals("the user name should be Daniel","Daniel",user.getDisplayName());

    }
}
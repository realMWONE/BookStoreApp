package com.comp3350_group10.bookstore.data.model;

import junit.framework.Assert;
import junit.framework.TestCase;

public class LoggedInUserTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public static void testGetUserId() {
        String[] randomID= new String[]{"123","456","789","000","111"};
        for(int i=0;i<randomID.length;i++) {
            LoggedInUser user = new LoggedInUser(randomID[i], "Harshal");
            assertEquals(user.getUserId(), randomID[i]);
        }
    }

    public static void testGetDisplayName() {
        String[] randomName= new String[]{"Dawarit","Daniel","Kevin","","Matt"};
        for(int i=0;i<randomName.length;i++) {
            LoggedInUser user = new LoggedInUser("123", randomName[i]);
            assertEquals(user.getDisplayName(), randomName[i]);
        }
    }
}
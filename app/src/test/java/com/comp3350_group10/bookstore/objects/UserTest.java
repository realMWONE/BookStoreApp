package com.comp3350_group10.bookstore.objects;

import com.comp3350_group10.bookstore.persistence.UserType;

import junit.framework.TestCase;

public class UserTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown()  {
    }

    public void testGetRealName() {
        //In this function, I default these fields:
        //userId= 1, password = 2, UserType=Employee
        String[] names = new String[]{"Dan", "Matt", "Harshal"};
        for (String name : names) {
            User u = new User(name, "1", "2", UserType.Employee);
            assertEquals(u.getRealName(), name);
        }
    }

    public void testGetUserType() {
        //In this function, I default these fields:
        //userName = Dan, userId= 1, password = 2
        User u = new User("Dan", "1", "2", UserType.Employee);
        assertEquals(u.getUserType(), UserType.Employee);
        User u1 = new User("Kevin", "2", "3", UserType.Manager);
        assertEquals(u1.getUserType(), UserType.Manager);
    }

    public void testGetUserID() {
        //In this function, I default these fields:
        //userName = Dan, Mat ; , password = 2,
        String[] userIds = new String[]{"1", "2", "3"};
        for (String userId : userIds) {
            User u = new User("Dan", userId, "2", UserType.Employee);
            assertEquals(u.getUserID(), userId);
            User u1 = new User("Mat", userId, "3", UserType.Manager);
            assertEquals(u1.getUserID(), userId);
        }
    }

    public void testGetPassword() {
        //In this function, I default these fields:
        //userName = Dan, Mat ; userId = 1,2
        String[] passwords = new String[]{"123a", "456b", "abc"};
        for (String password : passwords) {
            User u = new User("Dan", "1", password, UserType.Employee);
            assertEquals(u.getPassword(), password);
            User u1 = new User("Mat", "2", password, UserType.Manager);
            assertEquals(u1.getPassword(), password);
        }
    }

    public void testSetUserID() {
        //only change userId, other fields don't matter
        String[] userIds = new String[]{"1", "2", "3"};
        User u1 = new User("Dan", "a", "123456", UserType.Employee);
        User u2 = new User("Harshal", "b", "57691", UserType.Manager);
        User u3 = new User("Animesh", "c", "32123a", UserType.Manager);

        u1.setUserID(userIds[0]);
        u2.setUserID(userIds[1]);
        u3.setUserID(userIds[2]);

        assertEquals(u1.getUserID(), userIds[0]);
        assertEquals(u2.getUserID(), userIds[1]);
        assertEquals(u3.getUserID(), userIds[2]);
    }

    public void testSetPassword() {
        //only change userPassword, other fields don't matter
        String[] userPasswords = new String[]{"1234", "abcd", "1a2b3c"};
        User u1 = new User("Dan", "1", "1a", UserType.Employee);
        User u2 = new User("Harshal", "2", "2b", UserType.Manager);
        User u3 = new User("Animesh", "3", "3c", UserType.Manager);
        u1.setPassword(userPasswords[0]);
        u2.setPassword(userPasswords[1]);
        u3.setPassword(userPasswords[2]);
        assertEquals(u1.getPassword(), userPasswords[0]);
        assertEquals(u2.getPassword(), userPasswords[1]);
        assertEquals(u3.getPassword(), userPasswords[2]);
    }

    public void testAll(){
        testGetPassword();
        testGetRealName();
        testGetUserID();
        testGetUserType();
        testSetPassword();
        testSetUserID();
    }


}
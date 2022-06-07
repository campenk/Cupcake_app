package com.example.bit603_a3_kellycampen;

import org.junit.Test;

import static org.junit.Assert.*;

import androidx.room.Room;

import com.google.android.material.datepicker.CalendarConstraints;

import java.util.ArrayList;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    Utilities util = new Utilities();
    UserList userList = new UserList();



    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    /*****************
     * LOGIN ACTIVITY TESTS
     *****************/
    @Test
    public void userIsAdmin_MethodIsCorrect () {


        assertTrue(userList.userIsAdmin("admin", "CookieManagement84"));
        assertTrue(userList.userIsAdmin("Admin", "CookieManagement84"));
        assertTrue(userList.userIsAdmin("ADMIN", "CookieManagement84"));
        assertFalse(userList.userIsAdmin("admin", "Cookiemanagement84"));
        assertFalse(userList.userIsAdmin("", "CookieManagement84"));
        assertFalse(userList.userIsAdmin("admin", ""));
        assertFalse(userList.userIsAdmin("admin", "ILoveCookies!!"));
        assertFalse(userList.userIsAdmin("otherUsername", "CookieManagement84"));

    }

    @Test
    public void returnCurrentUser_MethodIsCorrect () {
        User lisa = new User();
        lisa.setUsername("lisa");
        lisa.setPassword("apple");
        lisa.setEmployeeNumber(12403258);
        lisa.setDateOfBirth("1980-06-04");
        lisa.setPhoneNumber("0212590928");
        lisa.setAddress("5 Main Street, Wellington");

        User katie = new User();
        katie.setUsername("katie");
        katie.setPassword("garfield123");
        katie.setEmployeeNumber(902576);
        katie.setDateOfBirth("1990-08-20");
        katie.setPhoneNumber("021259234");
        katie.setAddress("50 Seddon Street, Wellington");

        User frankie = new User();
        frankie.setUsername("frankie");
        frankie.setPassword("tenakoe");
        frankie.setEmployeeNumber(483948);
        frankie.setDateOfBirth("1999-01-10");
        frankie.setPhoneNumber("0223485930");
        frankie.setAddress("78 Brown Street, Wellington");

        List<User> users = new ArrayList<>();
        users.add(lisa);
        users.add(katie);
        users.add(frankie);

        assertEquals(lisa, userList.userExists("lisa", users));
        assertEquals(lisa, userList.userExists("Lisa", users));
        //assertEquals(katie, userList.userExists("KATIE", users));
        assertNull(userList.userExists("simon", users));
    }

    @Test
    public void dateValidator_isCorrect () {

        assertTrue(util.isDateValid("2022-03-13"));
        assertFalse(util.isDateValid("2019/02/28"));
        assertFalse(util.isDateValid("02/30/2019"));
    }






}


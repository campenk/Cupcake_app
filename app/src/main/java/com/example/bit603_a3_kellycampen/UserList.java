package com.example.bit603_a3_kellycampen;

import java.util.List;

public class UserList {

    // @kelly: i feel like this class was is a good initial abstraction of logic, but it wants to do further.
    // @kelly: i think part of it is in the name... perhaps calling it `Authenticator` would be better.
    // @kelly:   then you could have a constructor for it that takes a list of users (for simplicity), then:
    // @kelly:   - the userExists method just takes a username which is simpler
    // @kelly:   - the userIsAdmin could also take just the users name
    // @kelly:   OR
    // @kelly:   - you could have a different method called isAuthorised which essentially does what userIsAdmin does.


    final private static String adminUserName = "admin";
    final private static String adminPassword = "CookieManagement84";

    public static String getAdminUserName() {
        return adminUserName;
    }

    public static String getAdminPassword() {
        return adminPassword;
    }

    public Boolean userIsAdmin(String inputUsername, String inputPassword) {

        if (inputUsername.toLowerCase().equals(getAdminUserName()) && inputPassword.equals(getAdminPassword())) {
            return true;
        }
        return false;
    }

    public Boolean userExists (String username, List<User> users) {
        for (User user : users) {
            if (username.toLowerCase().equals(user.getUsername().toLowerCase())) {
                return true;            }
        }
        return false;
    }

    public User returnUser (String username, List<User> users) {
        User currentUser = null;
        for (User user : users) {
            if (username.toLowerCase().equals(user.getUsername().toLowerCase())) {
                currentUser = user;            }
        }
        return currentUser;
    }
}

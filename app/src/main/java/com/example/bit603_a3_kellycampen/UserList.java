package com.example.bit603_a3_kellycampen;

import java.util.List;

public class UserList {

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

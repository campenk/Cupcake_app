package com.example.bit603_a3_kellycampen;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = "User")
public class User {

    // @kelly: the pattern django uses, which i like, is that you can set a value on the User instance
    // @kelly:  that says if its logged in or not, and if its an admin or not. Then once you've established
    // @kelly:  that up front during some sort of authorization step (like in you login activity) you are then
    // @kelly:  onwards passing around a user object that has a simple boolean you can check when deciding behaviour.

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "UserID")
    Integer userID;

    @ColumnInfo(name = "Username")
    String username;

    @ColumnInfo(name = "Password")
    String password;

    @ColumnInfo(name = "DateOfBirth")
    String dateOfBirth;

    @ColumnInfo(name = "EmployeeNumber")
    Integer employeeNumber;

    @ColumnInfo(name = "PhoneNumber")
    String phoneNumber;

    @ColumnInfo(name = "Address")
    String address;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }  // @kelly: see note below re setting password.

    public void setPassword(String password) {
        this.password = password;
    }  // @kelly: if this were the real world we would not save passwords in plain text, instead we would hash them using some well known algo. password checking then involves taking the password the users logging in with and hashing it also, then comparing the hashed values. hashing is just fancy way of saying, do math based changes to the characters like replace every character with the next one in the alphabet, only way more complicated.

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(Integer employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

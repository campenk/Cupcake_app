package com.example.bit603_a3_kellycampen;

import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@androidx.room.Dao
public interface UserDao {

    @Insert
    public void addUser(User user);

    @Query("SELECT * FROM User")
    public List<User> getUsers();

    @Query("DELETE FROM User WHERE Username = :username")
    abstract void deleteByUsername(String username);


    @Query("DELETE FROM User")
    abstract void clearAllUsers();
}

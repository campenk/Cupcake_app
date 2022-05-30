package com.example.bit603_a3_kellycampen;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {User.class}, version = 2)

@TypeConverters({Converters.class})

public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao dao();
}

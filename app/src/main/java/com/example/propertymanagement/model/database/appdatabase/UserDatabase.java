package com.example.propertymanagement.model.database.appdatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.propertymanagement.model.database.appdao.UserDao;
import com.example.propertymanagement.model.pojo.User;

//@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {

//    public abstract UserDao userDao();
}

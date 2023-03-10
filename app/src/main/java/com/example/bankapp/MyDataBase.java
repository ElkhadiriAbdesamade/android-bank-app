package com.example.bankapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDataBase extends SQLiteOpenHelper
{

    private static final String DB_NAME = "Bank_db";
    private static final int DB_VESION = 2;



    public MyDataBase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VESION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TransactionDao.CREATE_TABLE);

        db.execSQL(UserDao.CREATE_TABLE);





    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(TransactionDao.DELETE_QUERY);
        db.execSQL(UserDao.DELETE_QUERY);

        onCreate(db);



    }
}

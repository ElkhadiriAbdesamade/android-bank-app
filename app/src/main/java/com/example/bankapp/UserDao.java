package com.example.bankapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UserDao extends MyDataBase
{
    Context context;
    public UserDao(Context c) {
        super(c);
        this.context=c;
    }

    //users
    public static final String TABLE_NAME="User";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_USER_NAME="username";
    public static final String COLUMN_PASSWORD="password";


    public static final String CREATE_TABLE=
            "create table "+TABLE_NAME +"("+COLUMN_ID+
                    " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    +COLUMN_USER_NAME + " TEXT,"
                    +COLUMN_PASSWORD +" TEXT);";


    public static final String DELETE_QUERY = "DROP table if exists " + TABLE_NAME;


    public void SignIn(User u)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(COLUMN_USER_NAME,u.getUsername());
        values.put(COLUMN_PASSWORD,u.getPassword());


        long result =db.insert(TABLE_NAME,null,values);

        if(result == -1){
            Toast.makeText(context, R.string.failed, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, R.string.add_succ, Toast.LENGTH_SHORT).show();
        }

    }

    public ArrayList<User> getAllInList() {
        ArrayList<User> users = new ArrayList<>();

        String select_query = "select * from " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Integer id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME));
                @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));

                User u=new User(id,username,password);
                users.add(u);

            } while (cursor.moveToNext());
        }
        return users;
    }



    public User getUser(String username) {

        User u = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_USER_NAME, COLUMN_PASSWORD}, "username=?",
                new String[]{String.valueOf(username)}, null, null, null, null);

        if (cursor.moveToFirst()) {
            @SuppressLint("Range")  Integer id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            @SuppressLint("Range")  String user_name = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME));
            @SuppressLint("Range")  String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));


            u=new User(id,user_name,password);
        }
        return u;
    }




}

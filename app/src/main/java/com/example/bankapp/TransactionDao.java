package com.example.bankapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TransactionDao extends MyDataBase{

    Context context;
    public TransactionDao(Context c) {
        super(c);
        this.context=c;
    }

    //Transaction
    public static final String TABLE_NAME="trans_bank";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_IMG="img";
    public static final String COLUMN_NUM_CAMP="num_camp";
    public static final String COLUMN_NUM_REF="num_ref";
    public static final String COLUMN_LABEL="label";
    public static final String COLUMN_DATE="date_op";
    public static final String COLUMN_DATE_VAL="date_val";
    public static final String COLUMN_PRICE="price";
    public static final String COLUMN_SOLDE="solde";




    public static final String CREATE_TABLE=
            "create table "+TABLE_NAME +"("+COLUMN_ID+
                    " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    +COLUMN_IMG + " INTEGER,"
                    +COLUMN_NUM_CAMP + " TEXT,"
                    +COLUMN_NUM_REF + " TEXT,"
                    +COLUMN_LABEL + " TEXT,"
                    +COLUMN_DATE + " TEXT,"
                    +COLUMN_PRICE + " TEXT,"
                    +COLUMN_SOLDE + " FLOAT);";



    public static final String DELETE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;


    public void AddTransaction(Transaction t)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(COLUMN_IMG,t.getIcon());
        values.put(COLUMN_NUM_CAMP,t.getNumCompte());
        values.put(COLUMN_NUM_REF,t.getNumRef());
        values.put(COLUMN_LABEL,t.getLabel());
        values.put(COLUMN_DATE,t.getDate());
        values.put(COLUMN_PRICE,t.getPrice());
        values.put(COLUMN_SOLDE,t.getSolde());


        long result =db.insert(TABLE_NAME,null,values);

        if(result == -1){
            Toast.makeText(context, R.string.failed, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, R.string.add_succ, Toast.LENGTH_SHORT).show();
        }

    }

    public ArrayList<Transaction> getAllInList() {
        ArrayList<Transaction> transactions = new ArrayList<>();

        String select_query = "select * from "+TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Integer id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") Integer icon = cursor.getInt(cursor.getColumnIndex(COLUMN_IMG));
                @SuppressLint("Range") String num_camp = cursor.getString(cursor.getColumnIndex(COLUMN_NUM_CAMP));
                @SuppressLint("Range") String num_ref = cursor.getString(cursor.getColumnIndex(COLUMN_NUM_REF));
                @SuppressLint("Range") String label = cursor.getString(cursor.getColumnIndex(COLUMN_LABEL));
                @SuppressLint("Range") String date_op = cursor.getString(cursor.getColumnIndex(COLUMN_DATE));
                @SuppressLint("Range") String price = cursor.getString(cursor.getColumnIndex(COLUMN_PRICE));
                @SuppressLint("Range") Double solde = cursor.getDouble(cursor.getColumnIndex(COLUMN_SOLDE));

                Transaction t=new Transaction(icon,label,price,date_op,num_camp,num_ref,solde);
                transactions.add(t);

            } while (cursor.moveToNext());
        }
        return transactions;
    }

}

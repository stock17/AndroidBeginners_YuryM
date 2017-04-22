package com.example.yury.myflashcardsproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.yury.myflashcardsproject.database.DBHelper;


public class DBCard {
    private DBHelper helper;
    private SQLiteDatabase database;

    public DBCard (Context context) {
        helper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }

    public void addCard(String question, String answer) {

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_QUESTION, question);
        values.put(DBHelper.COLUMN_ANSwER, answer);
        database.insert(DBHelper.DATABASE_NAME, null, values);
    }

}

   /* //Get all contacts from data base
    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> contactArrayList = new ArrayList<Contact>();

        Cursor cursor = database.rawQuery("SELECT  * FROM " + TABLE_CONTACTS, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Contact contact = new Contact();
            contact.setName(cursor.getString(1));
            contact.setLastName(cursor.getString(2));
            contact.setEmail(cursor.getString(3));
            contact.setPhoneNumber(cursor.getString(4));
            contactArrayList.add(contact);

            cursor.moveToNext();
        }

        cursor.close();
        return contactArrayList;
    }*/



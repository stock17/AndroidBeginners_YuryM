package com.example.yury.myflashcardsproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.yury.myflashcardsproject.MainActivity;
import static com.example.yury.myflashcardsproject.database.CardContract.CardEntry;


import java.util.ArrayList;
import java.util.List;


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
        values.put(CardEntry.COLUMN_QUESTION, question);
        values.put(CardEntry.COLUMN_ANSWER, answer);
        database.insert(CardEntry.TABLE_NAME, null, values);
    }

    public List<MainActivity.Card> getAllCards () {
        Cursor cursor = database.rawQuery("SELECT * FROM " + CardEntry.TABLE_NAME, null);
        cursor.moveToFirst();
        List<MainActivity.Card> list = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            list.add(new MainActivity.Card(cursor.getString(1), cursor.getString(2)));
            cursor.moveToNext();
        }
        return list;
    }

}




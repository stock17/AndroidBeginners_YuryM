package com.example.yury.myflashcardsproject.database;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

import com.example.yury.myflashcardsproject.MainActivity;

public final class CardContract {

    private CardContract() {};

    static final String AUTHORITY = "com.example.yury.myflashcardsproject";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    static final String PATH = "cards";
    public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH);

    public static final class CardEntry implements BaseColumns {

        public static final String TABLE_NAME = "cards";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_ANSWER = "answer";


        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + PATH;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "/" + PATH;

    }


}

package com.nitkkr.techspardha.Database_Internal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;

public class DatabaseHelper extends SQLiteOpenHelper {

        // Table Name
        public static final String TABLE_NAME = "MyEventList";

        // Table columns



        public static final String NAME= "myEvents";
        public static final String CATEGORY= "Category";
        public static final String BANNER= "Banner";




        // Database Information
        static final String DB_NAME = "OFFLINE.DB";

        // database version
        static final int DB_VERSION = 3;

        // Creating table query
        private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR, " + CATEGORY + " VARCHAR, " + BANNER + " VARCHAR);";

        public DatabaseHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }


}

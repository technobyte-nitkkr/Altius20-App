package com.nitkkr.techspardha.Database_Internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import static com.nitkkr.techspardha.Database_Internal.DatabaseHelper.NAME;
import static com.nitkkr.techspardha.Database_Internal.DatabaseHelper.TABLE_NAME;

public class DBManager {
    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String Name,String Catergory,String Banner) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(NAME, Name);
        contentValue.put(DatabaseHelper.CATEGORY, Catergory);
        contentValue.put(DatabaseHelper.BANNER, Banner);

        database.insert(TABLE_NAME, null, contentValue);
    }

    public boolean ifNumberExists(String strNumber)
    {
        Cursor cursor = null;
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE  " + NAME + " = '" + strNumber +"'";
        cursor= database.rawQuery(selectQuery,null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
    public void deleteAll()
    {

        database.execSQL("delete from "+ TABLE_NAME);
        database.close();
    }








}

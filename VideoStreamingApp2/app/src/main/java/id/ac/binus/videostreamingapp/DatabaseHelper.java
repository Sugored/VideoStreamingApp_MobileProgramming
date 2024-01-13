package id.ac.binus.videostreamingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DatabaseName = "Accounts.db";

    public DatabaseHelper(Context context) {
        super(context, "Accounts.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase accountDatabase) {
        accountDatabase.execSQL("create Table users(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase accountDatabase, int i, int i1) {
        accountDatabase.execSQL("drop Table if exists users");
    }

    public Boolean addAccount(String username, String password){
        SQLiteDatabase accountDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = accountDatabase.insert("users", null, contentValues);

        if(result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean checkUser(String username){
        SQLiteDatabase accountDatabase = this.getWritableDatabase();
        Cursor cursor = accountDatabase.rawQuery("Select * from users where username = ?",
                new String[] {username});

        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean checkCredentials(String username, String password){
        SQLiteDatabase accountDatabase = this.getWritableDatabase();
        Cursor cursor = accountDatabase.rawQuery("Select * from users where username = ? and password = ?",
                new String[] {username, password});

        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }
}

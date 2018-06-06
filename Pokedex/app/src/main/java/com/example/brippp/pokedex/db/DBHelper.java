package com.example.brippp.pokedex.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "STUDENT";
    private static  final int DB_Version = 1;
    private static final String table = "Favorites";

    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_Version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = "CREATE TABLE " + table + "(name varchar NOT NULL PRIMARY KEY);";
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Insert a new Favorite
     * @param name Pokemon which should get added
     */
    public void insertFavorite(String name){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name);

        db.insert("Favorites", null, values);

        db.close();
    }

    /**
     * Delete a Favorite
     * @param name Pokemon which should get deleted
     */
    public void deleteFavorite(String name){
        SQLiteDatabase db = this.getWritableDatabase();

        String whereClause = "name=?";
        String[] whereArgs = new String[] { String.valueOf(name) };

        db.delete("Favorites", whereClause, whereArgs);

        db.close();
    }

    /**
     * Read all Favorites
     * @return Favorite String List
     */
    public ArrayList<String> readFavorites(){
        ArrayList<String> favPokemons = new ArrayList<>();

        String[] projection = {"name"};

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(table, projection, null, null, null,null,null);
        while (cursor.moveToNext()){
            favPokemons.add(cursor.getString(0));
        }
        return favPokemons;
    }

    /**
     * Check if Pokemon is a favorite
     * @param name Name of the Pokemon
     * @return boolean, true if favorite, false if not
     */
    public boolean isFavorite(String name){
        String[] projection = {"name"};

        String where = "name" + " = ?";
        String[] args = {name};

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(table, projection, where, args, null,null,null);
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }
}

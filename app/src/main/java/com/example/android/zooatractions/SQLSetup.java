package com.example.android.zooatractions;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by Android on 6/27/2017.
 */

public class SQLSetup extends SQLiteOpenHelper {

    private static final int VERSION =1;
    private static final String NAME ="My_db";
    private static final  String TABLE_NAME = "Animals";
    private static final  String ANIMAL_SPECIES = "species";
    private static final  String ANIMAL_NUMBER ="number";
    private static final  String ANIMAL_GROUP = "grouping";
    private static final  String ANIMAL_IMAGE_LOCATION= "image";
    private static final  String ANIMAL_SOUND_LOCATION = "sound";
    private static final  String ANIMAL_DESCRIPTION = "descripition";

    public SQLSetup(Context context) {
        super(context, NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+ " (" +ANIMAL_GROUP+" TEXT, "+ANIMAL_NUMBER+" TEXT, " +ANIMAL_IMAGE_LOCATION+
                " TEXT, "+ANIMAL_SOUND_LOCATION+" TEXT, "+ANIMAL_DESCRIPTION +" TEXT, " +ANIMAL_SPECIES+ " TEXT PRIMARY KEY)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXITS "+ TABLE_NAME);
            onCreate(db);


    }
    public void SaveAnimal (Animals a){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(ANIMAL_SPECIES,a.getSpecies());
        content.put(ANIMAL_DESCRIPTION,a.getDescription());
        content.put(ANIMAL_GROUP,a.getGroup());
        content.put(ANIMAL_IMAGE_LOCATION,a.getImageLocation());
        content.put(ANIMAL_SOUND_LOCATION,a.getSoundLocation());
        content.put(ANIMAL_NUMBER,a.getNumber());
        database.insert(TABLE_NAME,null,content);
        database.close();
    }

    public String[] getUniqueGroups(){

        String[] a = new String[5];
        int count = 0;
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT DISTINCT " +ANIMAL_GROUP+ " FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(query,null);
        if(cursor.moveToFirst())
        {do {

            a[count] = cursor.getString(0);
            count++;
        }while (cursor.moveToNext());
        }
        database.close();
        return  a;
    }

    public Animals[] getByGroup(String g){
        Animals[] a = new Animals[5];
        int count = 0;
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT * FROM " +TABLE_NAME+ " WHERE " + ANIMAL_GROUP +" = '" +g+ "' ";
        Cursor cursor = database.rawQuery(query,null);
        if(cursor.moveToFirst())
        {do {   //
            Log.d(TAG, "getByGroup: " +count+ cursor.getString(5)+cursor.getString(4)+cursor.getString(3)+cursor.getString(2)+cursor.getString(1)+cursor.getString(0));
            a[count] = new Animals(cursor.getString(5),cursor.getString(4),cursor.getString(3),cursor.getString(2),cursor.getString(1),cursor.getString(0));
            count++;
        }while (cursor.moveToNext());
        }
        database.close();
        return a;
    }

    public Animals getBySpecies(String s){


        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT * FROM " +TABLE_NAME+ " WHERE " + ANIMAL_SPECIES +" = '" +s+ "' ";
        Cursor cursor = database.rawQuery(query,null);
        if(cursor.moveToFirst())
        {

            Log.d(TAG, "getByGroup: " + cursor.getString(5)+cursor.getString(4)+cursor.getString(3)+cursor.getString(2)+cursor.getString(1)+cursor.getString(0));
           Animals a = new Animals(cursor.getString(5),cursor.getString(4),cursor.getString(3),cursor.getString(2),cursor.getString(1),cursor.getString(0));

            database.close();
            return a;

        }
        return null;
    }


}

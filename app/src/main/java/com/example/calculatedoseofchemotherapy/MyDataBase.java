package com.example.calculatedoseofchemotherapy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="patients.db";
    public static final String TABLE_NAME="patients_table";
    public static final String COL_1="ID";
    public static final String COL_2="ID_P";
    public static final String COL_3="first_NAME";
    public static final String COL_4="last_NAME";
    public static final String COL_5="name_of_Dose";
    public static final String COL_6="Desired_Dose_Unit";
    public static final String COL_7="Date";



    public MyDataBase(Context context) {
        super(context, DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME+"(ID INTEGER PRIMARY KEY ,ID_P INTEGER, first_NAME TEXT ,last_NAME TEXT,name_of_Dose TEXT ,Desired_Dose_Unit REAL,Date TEXT )");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);

    }
    public boolean insertData(String ID_P,String first_NAME ,String last_NAME , String name_of_Dose,String Desired_Dose_Unit ,String Date  ){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,ID_P);
        contentValues.put(COL_3,first_NAME);
        contentValues.put(COL_4,last_NAME);
        contentValues.put(COL_5,name_of_Dose);
        contentValues.put(COL_6,Desired_Dose_Unit);
        contentValues.put(COL_7,Date);

        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result ==-1)
            return false;
        else
            return true;


    }
    public Integer deleteData (String ID){
        SQLiteDatabase db =this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID=?",new String[]{ID});
    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME,null);

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;
    }
    public Cursor search(String ID_P){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + COL_2 + " = ?", new String[] {ID_P});

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;


    }

    public boolean UpdateData( String ID,String ID_P,String first_NAME ,String last_NAME , String name_of_Dose,String Desired_Dose_Unit ,String Date ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,ID);
        contentValues.put(COL_2,ID_P);
        contentValues.put(COL_3,first_NAME);
        contentValues.put(COL_4,last_NAME);
        contentValues.put(COL_5,name_of_Dose);
        contentValues.put(COL_6,Desired_Dose_Unit);
        contentValues.put(COL_7,Date);
        db.update(TABLE_NAME, contentValues, "ID = ? ", new String[]{ID});
        return true;
    }





}

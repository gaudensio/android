package com.example.asus_pc.uts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataHelper extends SQLiteOpenHelper {
    private  static final String DATABASE_NAME = "musik.db";

    private static final String TABLE_NAME = "alat";

    private static final int DATABASE_VERSION = 1;

    private static final String SID ="id";
    private static final String NAMA ="nama";
    private static final String TIPE = "tipe";
    private static final String HARGA = "harga";
    private static final String LOKASI = "lokasi";
    private static final String DESKRIPSI = "deskripsi";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + " ("
            + SID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAMA + " VARCHAR(255), "
            + TIPE + " VARCHAR(255), "
            + HARGA + " VARCHAR(255), "
            + LOKASI + " VARCHAR(255), "
            + DESKRIPSI + " VARCHAR(255) "
            + ");";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;


    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public MyDataHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}

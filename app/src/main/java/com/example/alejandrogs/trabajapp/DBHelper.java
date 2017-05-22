package com.example.alejandrogs.trabajapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alejandrogs on 20/05/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Nombre de la base de datos
    private static final String DATABASE_NAME = "DBTrabaja";

    // Nombre de las tablas
    private static final String TABLE_Usuario= "Usuarios";

    // Nombre de las columnas de la tabla Notes
    private static final String usuario_mail= "mail";
    private static final String usuario_id= "id";
    private static final String usuario_name = "name";
    private static final String usuario_adress = "adress";
    private static final String usuario_age = "age";
    private static final String usuario_city = "city";
    private static final String usuario_country = "country";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CRATE TABLE "+TABLE_Usuario+"("+usuario_mail+" TEXT PRIMARY KEY," +
                ""+usuario_id+" AUTOINCREMENT,"+usuario_name+" TEXT,"+usuario_adress+" TEXT," +
                ""+usuario_age+" TEXT,"+usuario_city+" TEXT,"+usuario_country+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Usuario);
        onCreate(db);
    }


}

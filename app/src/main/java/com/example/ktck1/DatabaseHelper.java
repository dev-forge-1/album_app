package com.example.ktck1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "album.db";
    public static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // ===== ALBUM =====
        db.execSQL(
                "CREATE TABLE Album (" +
                        "album_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "album_name TEXT," +
                        "cover_image INTEGER)"
        );

        // ===== PHOTO =====
        db.execSQL(
                "CREATE TABLE Photo (" +
                        "photo_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "image INTEGER," +
                        "album_id INTEGER)"
        );

        // ===== TRASH PHOTO =====
        db.execSQL(
                "CREATE TABLE TrashPhoto (" +
                        "trash_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "image INTEGER," +
                        "album_id INTEGER," +
                        "deleted_at TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Album");
        db.execSQL("DROP TABLE IF EXISTS Photo");
        db.execSQL("DROP TABLE IF EXISTS TrashPhoto");
        onCreate(db);
    }
}

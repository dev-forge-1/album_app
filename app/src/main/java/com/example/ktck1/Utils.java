package com.example.ktck1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Utils {

    public static void insertSampleDataOnce(Context context) {
        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM Album", null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();

        if (count == 0) {
            insertSampleData(context);
        }
    }

    private static void insertSampleData(Context context) {
        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        // ===== ALBUM =====
        ContentValues a1 = new ContentValues();
        a1.put("album_name", "Album1");
        a1.put("cover_image", R.drawable.a1);
        db.insert("Album", null, a1);

        ContentValues a2 = new ContentValues();
        a2.put("album_name", "Album2");
        a2.put("cover_image", R.drawable.a2);
        db.insert("Album", null, a2);

        // ===== PHOTO =====
        ContentValues p1 = new ContentValues();
        p1.put("image", R.drawable.p1);
        p1.put("album_id", 1);
        db.insert("Photo", null, p1);

        ContentValues p2 = new ContentValues();
        p2.put("image", R.drawable.p2);
        p2.put("album_id", 1);
        db.insert("Photo", null, p2);

        ContentValues p3 = new ContentValues();
        p3.put("image", R.drawable.p3);
        p3.put("album_id", 2);
        db.insert("Photo", null, p3);

        ContentValues p4 = new ContentValues();
        p4.put("image", R.drawable.p4);
        p4.put("album_id", 2);
        db.insert("Photo", null, p4);

        ContentValues p5 = new ContentValues();
        p5.put("image", R.drawable.p5);
        p5.put("album_id", 2);
        db.insert("Photo", null, p5);

        ContentValues p6 = new ContentValues();
        p6.put("image", R.drawable.p6);
        p6.put("album_id", 2);
        db.insert("Photo", null, p6);

        ContentValues p7 = new ContentValues();
        p7.put("image", R.drawable.p7);
        p7.put("album_id", 2);
        db.insert("Photo", null, p7);
    }
}

package com.example.ktck1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class PhotoDAO {

    DatabaseHelper dbHelper;

    public PhotoDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public ArrayList<Photo> getAllPhoto() {
        ArrayList<Photo> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Photo", null);
        while (cursor.moveToNext()) {
            int albumId = cursor.getInt(cursor.getColumnIndexOrThrow("album_id"));
            int image = cursor.getInt(cursor.getColumnIndexOrThrow("image"));
            list.add(new Photo(albumId, image));
        }
        cursor.close();
        return list;
    }

    public ArrayList<Photo> getPhotoByAlbum(int albumId) {
        ArrayList<Photo> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM Photo WHERE album_id=?",
                new String[]{String.valueOf(albumId)}
        );

        while (cursor.moveToNext()) {
            int image = cursor.getInt(cursor.getColumnIndexOrThrow("image"));
            list.add(new Photo(albumId, image));
        }
        cursor.close();
        return list;
    }

    // ⭐ XÓA → CHUYỂN SANG THÙNG RÁC
    public void moveToTrash(Photo photo) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("image", photo.getImage());
        values.put("album_id", photo.getAlbumId());
        values.put("deleted_at", System.currentTimeMillis());

        db.insert("TrashPhoto", null, values);

        db.delete("Photo", "image=?",
                new String[]{String.valueOf(photo.getImage())});
    }
}

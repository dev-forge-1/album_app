package com.example.ktck1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class TrashDAO {

    DatabaseHelper dbHelper;

    public TrashDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public ArrayList<TrashPhoto> getAllTrash() {
        ArrayList<TrashPhoto> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM TrashPhoto", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("trash_id"));
            int image = cursor.getInt(cursor.getColumnIndexOrThrow("image"));
            int albumId = cursor.getInt(cursor.getColumnIndexOrThrow("album_id"));

            list.add(new TrashPhoto(id, image, albumId));
        }
        cursor.close();
        return list;
    }

    // ♻️ KHÔI PHỤC
    public void restore(TrashPhoto photo) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("image", photo.getImage());
        values.put("album_id", photo.getAlbumId());

        db.insert("Photo", null, values);
        db.delete("TrashPhoto", "trash_id=?",
                new String[]{String.valueOf(photo.getId())});
    }

    // ❌ XÓA VĨNH VIỄN
    public void deleteForever(int trashId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("TrashPhoto", "trash_id=?",
                new String[]{String.valueOf(trashId)});
    }
}

package com.example.ktck1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class AlbumDAO {

    DatabaseHelper dbHelper;

    public AlbumDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public ArrayList<Album> getAllAlbum() {
        ArrayList<Album> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Album", null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int image = cursor.getInt(2);
            list.add(new Album(id, name, image));
        }

        cursor.close();
        return list;
    }
}

package com.example.ktck1;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

public class FullImageHelper {

    public static void open(Context context, ArrayList<Photo> list, int position) {
        int[] images = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            images[i] = list.get(i).getImage();
        }

        Intent intent = new Intent(context, FullImageActivity.class);
        intent.putExtra("images", images);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }
}

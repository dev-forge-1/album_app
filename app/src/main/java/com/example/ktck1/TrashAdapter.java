package com.example.ktck1;

import android.content.Context;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;

public class TrashAdapter extends BaseAdapter {

    Context context;
    ArrayList<TrashPhoto> list;

    public TrashAdapter(Context context, ArrayList<TrashPhoto> list) {
        this.context = context;
        this.list = list;
    }

    @Override public int getCount() { return list.size(); }
    @Override public Object getItem(int i) { return list.get(i); }
    @Override public long getItemId(int i) { return i; }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        if (view == null) {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.item_photo, parent, false);
        }

        ImageView img = view.findViewById(R.id.imgPhoto);
        img.setImageResource(list.get(i).getImage());

        return view;
    }
}

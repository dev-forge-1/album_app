package com.example.ktck1;

import android.content.Context;
import android.view.*;
import android.widget.*;
import java.util.ArrayList;

public class AlbumAdapterGrid extends BaseAdapter {

    Context context;
    ArrayList<Album> list;

    public AlbumAdapterGrid(Context context, ArrayList<Album> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() { return list.size(); }

    @Override
    public Object getItem(int i) { return list.get(i); }

    @Override
    public long getItemId(int i) { return i; }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        if (view == null) {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.item_album, parent, false);
        }

        ImageView img = view.findViewById(R.id.imgAlbum);
        TextView tv = view.findViewById(R.id.tvAlbumName);

        Album album = list.get(i);
        img.setImageResource(album.getImage());
        tv.setText(album.getName());

        return view;
    }
}

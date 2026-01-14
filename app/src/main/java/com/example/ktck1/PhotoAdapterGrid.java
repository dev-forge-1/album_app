package com.example.ktck1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashSet;

public class PhotoAdapterGrid extends ArrayAdapter<Photo> {

    Context context;
    ArrayList<Photo> list;
    HashSet<Integer> selectedPositions = new HashSet<>();
    boolean isSelectionMode = false;

    public PhotoAdapterGrid(Context context, ArrayList<Photo> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_photo, parent, false);
        }

        ImageView img = convertView.findViewById(R.id.imgPhoto);
        View overlay = convertView.findViewById(R.id.overlay);

        Photo photo = list.get(position);
        img.setImageResource(photo.getImage());

        // Hiển thị overlay nếu được chọn
        overlay.setVisibility(
                selectedPositions.contains(position)
                        ? View.VISIBLE
                        : View.GONE
        );

        // CLICK
        convertView.setOnClickListener(v -> {

            if (isSelectionMode) {
                toggleSelection(position);
                notifyDataSetChanged();
            } else {
                // Click thường → xem full
                FullImageHelper.open(context, list, position);
            }
        });

        // LONG CLICK → vào chế độ chọn
        convertView.setOnLongClickListener(v -> {
            isSelectionMode = true;
            toggleSelection(position);
            notifyDataSetChanged();
            return true;
        });

        return convertView;
    }

    private void toggleSelection(int position) {
        if (selectedPositions.contains(position)) {
            selectedPositions.remove(position);
        } else {
            selectedPositions.add(position);
        }
    }

    public ArrayList<Photo> getSelectedPhotos() {
        ArrayList<Photo> result = new ArrayList<>();
        for (int pos : selectedPositions) {
            result.add(list.get(pos));
        }
        return result;
    }

    public void clearSelection() {
        selectedPositions.clear();
        isSelectionMode = false;
        notifyDataSetChanged();
    }

    public boolean isSelectionMode() {
        return isSelectionMode;
    }
}

package com.example.ktck1;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class PhotoFragment extends Fragment {

    private static final String KEY_ID = "album_id";

    GridView gridView;
    PhotoAdapterGrid adapter;
    Button btnDelete;

    ArrayList<Photo> photoList = new ArrayList<>();

    // Mở TẤT CẢ ảnh
    public static PhotoFragment newInstance() {
        PhotoFragment f = new PhotoFragment();
        Bundle b = new Bundle();
        b.putInt(KEY_ID, -1);
        f.setArguments(b);
        return f;
    }

    // Mở ảnh theo album
    public static PhotoFragment newInstance(int albumId) {
        PhotoFragment f = new PhotoFragment();
        Bundle b = new Bundle();
        b.putInt(KEY_ID, albumId);
        f.setArguments(b);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_photo, container, false);

        gridView = view.findViewById(R.id.gridPhoto);
        btnDelete = view.findViewById(R.id.btnDelete);

        // KHỞI TẠO ADAPTER 1 LẦN DUY NHẤT
        adapter = new PhotoAdapterGrid(getContext(), photoList);
        gridView.setAdapter(adapter);

        // NÚT XÓA NHIỀU ẢNH
        btnDelete.setOnClickListener(v -> {

            if (!adapter.isSelectionMode()) return;

            new AlertDialog.Builder(getContext())
                    .setTitle("Xóa ảnh")
                    .setMessage("Bạn có chắc muốn xóa các ảnh đã chọn?")
                    .setPositiveButton("Xóa", (dialog, which) -> {

                        ArrayList<Photo> selected =
                                adapter.getSelectedPhotos();

                        PhotoDAO dao = new PhotoDAO(getContext());
                        for (Photo p : selected) {
                            dao.moveToTrash(p);

                        }

                        adapter.clearSelection();
                        loadData();
                    })
                    .setNegativeButton("Hủy", null)
                    .show();
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData(); // CHỈ LOAD Ở ĐÂY
    }

    private void loadData() {
        if (getArguments() == null) return;

        int albumId = getArguments().getInt(KEY_ID);
        PhotoDAO dao = new PhotoDAO(getContext());

        photoList.clear(); // ⭐ DÒNG QUAN TRỌNG CHỐNG LẶP ẢNH ⭐

        if (albumId == -1) {
            photoList.addAll(dao.getAllPhoto());
        } else {
            photoList.addAll(dao.getPhotoByAlbum(albumId));
        }

        adapter.notifyDataSetChanged();
    }
}

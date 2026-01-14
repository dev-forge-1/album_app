package com.example.ktck1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class AlbumFragment extends Fragment {

    GridView gridView;
    AlbumAdapterGrid adapter;
    ArrayList<Album> albumList = new ArrayList<>();

    LinearLayout layoutTrash;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_album, container, false);

        gridView = view.findViewById(R.id.gridAlbum);
        layoutTrash = view.findViewById(R.id.layoutTrash);

        adapter = new AlbumAdapterGrid(getContext(), albumList);
        gridView.setAdapter(adapter);

        // CLICK ALBUM
        gridView.setOnItemClickListener((parent, v, position, id) -> {
            Album album = albumList.get(position);

            PhotoFragment fragment =
                    PhotoFragment.newInstance(album.getId());

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .addToBackStack(null)
                    .commit();
        });

        // CLICK THÙNG RÁC
        layoutTrash.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new TrashFragment())
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        AlbumDAO dao = new AlbumDAO(getContext());
        albumList.clear();
        albumList.addAll(dao.getAllAlbum());
        adapter.notifyDataSetChanged();
    }
}

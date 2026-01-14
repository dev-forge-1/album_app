package com.example.ktck1;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.*;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class TrashFragment extends Fragment {

    GridView gridView;
    TrashAdapter adapter;
    ArrayList<TrashPhoto> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_trash, container, false);
        gridView = view.findViewById(R.id.gridTrash);

        adapter = new TrashAdapter(getContext(), list);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, v, position, id) -> {
            TrashPhoto photo = list.get(position);

            new AlertDialog.Builder(getContext())
                    .setTitle("Ảnh đã xóa")
                    .setItems(
                            new CharSequence[]{"Khôi phục", "Xóa vĩnh viễn"},
                            (dialog, which) -> {
                                TrashDAO dao = new TrashDAO(getContext());

                                if (which == 0) {
                                    dao.restore(photo);
                                } else {
                                    dao.deleteForever(photo.getId());
                                }
                                loadData();
                            }
                    ).show();
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        TrashDAO dao = new TrashDAO(getContext());
        list.clear();
        list.addAll(dao.getAllTrash());
        adapter.notifyDataSetChanged();
    }
}

package com.example.ktck1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Chỉ insert dữ liệu mẫu 1 lần
        Utils.insertSampleDataOnce(this);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);

        // MẶC ĐỊNH MỞ TAB ẢNH (TẤT CẢ ẢNH)
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, PhotoFragment.newInstance())
                .commit();

        bottomNav.setSelectedItemId(R.id.menu_photo);

        bottomNav.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.menu_photo) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, PhotoFragment.newInstance())
                        .commit();
                return true;
            }

            if (item.getItemId() == R.id.menu_album) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new AlbumFragment())
                        .commit();
                return true;
            }

            return false;
        });
    }
}

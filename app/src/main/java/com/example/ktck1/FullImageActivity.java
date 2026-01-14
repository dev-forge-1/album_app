package com.example.ktck1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class FullImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        ViewPager2 viewPager = findViewById(R.id.viewPager);

        int[] images = getIntent().getIntArrayExtra("images");
        int position = getIntent().getIntExtra("position", 0);

        FullImagePagerAdapter adapter =
                new FullImagePagerAdapter(images);

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position, false);
    }
}

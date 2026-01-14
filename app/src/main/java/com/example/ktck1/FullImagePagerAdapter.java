package com.example.ktck1;

import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FullImagePagerAdapter
        extends RecyclerView.Adapter<FullImagePagerAdapter.ViewHolder> {

    private int[] images;

    public FullImagePagerAdapter(int[] images) {
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {

        ImageView img = new ImageView(parent.getContext());
        img.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        img.setBackgroundColor(0xFF000000);
        img.setScaleType(ImageView.ScaleType.FIT_CENTER);

        return new ViewHolder(img);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder, int position) {

        holder.imageView.setImageResource(images[position]);

        // reset zoom khi chuyển ảnh
        holder.imageView.setScaleX(1f);
        holder.imageView.setScaleY(1f);
        holder.zoomed = false;

        holder.imageView.setOnClickListener(v -> {
            long now = System.currentTimeMillis();

            // DOUBLE TAP < 300ms
            if (now - holder.lastClickTime < 300) {

                if (holder.zoomed) {
                    holder.imageView.animate()
                            .scaleX(1f)
                            .scaleY(1f)
                            .setDuration(200)
                            .start();
                } else {
                    holder.imageView.animate()
                            .scaleX(2f)
                            .scaleY(2f)
                            .setDuration(200)
                            .start();
                }
                holder.zoomed = !holder.zoomed;
            }

            holder.lastClickTime = now;
        });
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        boolean zoomed = false;
        long lastClickTime = 0;

        ViewHolder(@NonNull ImageView itemView) {
            super(itemView);
            imageView = itemView;
        }
    }
}

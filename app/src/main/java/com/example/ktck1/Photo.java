package com.example.ktck1;

public class Photo {
    private int albumId;
    private int image;

    public Photo(int albumId, int image) {
        this.albumId = albumId;
        this.image = image;
    }

    public int getAlbumId() { return albumId; }
    public int getImage() { return image; }
}

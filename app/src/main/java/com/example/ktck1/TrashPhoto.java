package com.example.ktck1;

public class TrashPhoto {
    private int id;
    private int image;
    private int albumId;

    public TrashPhoto(int id, int image, int albumId) {
        this.id = id;
        this.image = image;
        this.albumId = albumId;
    }

    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }

    public int getAlbumId() {
        return albumId;
    }
}

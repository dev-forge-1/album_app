package com.example.ktck1;

public class Album {
    private int id;
    private String name;
    private int image;

    public Album(int id, String name, int image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getImage() { return image; }
}

package com.example.sanghyuk.recyclerpractice;

/**
 * Created by Sanghyuk on 2017-09-16.
 */

public class Item {
    int image;
    String imagetitle;
    public int getImage() {
        return image;
    }
    public String getImagetitle() {
        return imagetitle;
    }
    public Item(int image, String imagetitle) {
        this.image=image;
        this.imagetitle=imagetitle;
    }
}

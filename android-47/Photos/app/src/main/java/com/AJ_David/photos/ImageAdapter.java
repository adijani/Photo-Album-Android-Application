package com.AJ_David.photos;


import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.net.Uri;

import java.util.ArrayList;

/**
 * @author David Duong dd831
 * @author Aditya Jani amj165
 */

public class ImageAdapter extends BaseAdapter {
    private Context context;


    public static ArrayList<Photo> uris = new ArrayList<>();
    
    public ImageAdapter(Context c) {
        context = c;
    }

    @Override
    public int getCount() {
        return uris.size();
    }

    @Override
    public Object getItem(int index) {
        return null;
    }

    @Override
    public long getItemId(int index) {
        return 0;
    }

    public View getView(int index, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageURI(uris.get(index).getUri());
        return imageView;
    }

    /**
     * Gets the photo at a specified index
     */
    public Photo getPhoto(int index){
        return uris.get(index);
    }

    /**
     * Adds a new photo object
     */
    public void add(Uri add) {
        uris.add(new Photo(add));
    }

    /**
     * Adds a new photo object to arraylist
     */
    public void add(Photo add) {
        uris.add(add);
    }

    /**
     * Removes photo at specific index from arraylist
     */
    public void remove(int index) {
        uris.remove(index);
    }

    /**
     * Gives back arraylist of photos
     */
    public ArrayList<Photo> getPhotos() {
        return uris;
    }

    /**
     * Clears arraylist
     */
    public void clear() {
        uris.clear();
    }

}


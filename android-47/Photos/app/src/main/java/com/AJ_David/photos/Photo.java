package com.AJ_David.photos;

import android.net.Uri;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author David Duong dd831
 * @author Aditya Jani amj165
 */

public class Photo implements Serializable {
    public ArrayList<Tag> tags = new ArrayList<Tag>();
    private Uri uri;


    public Photo(Uri uri) {
        this.uri = uri; }

    public void addTag(String type, String data){
        tags.add(new Tag(type, data));
        return;
    }

    public void addTag(String tag){
        String data = tag.substring(tag.indexOf("=")+1);
        tag = tag.substring(0,tag.indexOf("="));
        tags.add(new Tag(tag, data));
        return;
    }

    public Uri getUri() {

        return uri;
    }

    public String toString() {
        String str = uri.toString();

        for (Tag t : tags) {
            str = str + "\nTAG:" + t;
        }

        return str;

    }

}

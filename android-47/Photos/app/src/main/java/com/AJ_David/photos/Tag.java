package com.AJ_David.photos;

import java.io.Serializable;

/**
 * @author David Duong dd831
 * @author Aditya Jani amj165
 */

public class Tag implements Serializable {
    public String type;
    private String data;

    public Tag(String type, String data){
        this.type = type;
        this.data = data;
    }

    public void setData(String data){

        this.data = data;
    }

    public String getData(){

        return data;
    }

    public String toString(){

        return type+"="+data;
    }
}
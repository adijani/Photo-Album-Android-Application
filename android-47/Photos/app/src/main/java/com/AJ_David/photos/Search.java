package com.AJ_David.photos;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author David Duong dd831
 * @author Aditya Jani amj165
 */

public class Search extends AppCompatActivity {
    private RadioButton loc, person;
    private RadioGroup rg;
    private EditText tagData;
    private Button search, cancel;
    private int type = -1;
    private static ArrayList<Photo> searched = new ArrayList<>();
    private static ArrayList<Photo> sList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        rg = (RadioGroup) findViewById(R.id.radiogroup);

        loc = (RadioButton) findViewById(R.id.location);
        person = (RadioButton) findViewById(R.id.person);

        tagData = (EditText) findViewById(R.id.data);
        search = (Button) findViewById(R.id.search);
        cancel = (Button) findViewById(R.id.cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> tags = new ArrayList<>();
                searched.clear();
                sList.clear();
                int i = 0;
                read();

                type = rg.getCheckedRadioButtonId();
                if (!tagData.getText().toString().equals("")) {

                    switch (type) {
                        case 2131165275:
                            for (Photo p : searched) {
                                for (Tag t : p.tags){
                                    if (t.getData().contains(tagData.getText().toString()) &&
                                            t.type.equals("Location")){
                                        sList.add(p);
                                        i++;
                                        break;
                                    }
                                }
                            }
                            write();
                            finish();
                            break;
                        case 2131165294:
                            for (Photo p : searched) {
                                for (Tag t : p.tags){
                                    if (t.getData().contains(tagData.getText().toString()) &&
                                            t.type.equals("Person")){
                                        sList.add(p);
                                        i++;
                                        break;
                                    }
                                }
                            }
                            write();
                            finish();
                            break;
                        default:
                            break;

                    }

                    Intent intent = new Intent(getApplicationContext(), AlbumView.class);
                    HomeScreen.albumName="SearchRes";
                    startActivity(intent);

                }
            }
        });

        read();
    }

    public void read() {
        String[] strings = {};
        ArrayList<String> masterList = new ArrayList<>();

        try {
            FileInputStream fileInputStream = openFileInput("albums.albm");

            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            ArrayList<String> list = new ArrayList<String>();
            String lineIn;

            while ((lineIn = bufferedReader.readLine()) != null) {
                list.add(lineIn);
            }

            for (String s : list) {
                try {
                    FileInputStream fileInputStream2 = openFileInput(s + ".list");

                    InputStreamReader inputStreamReader2 = new InputStreamReader(fileInputStream2);
                    BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader2);

                    while ((lineIn = bufferedReader2.readLine()) != null) {
                        masterList.add(lineIn);
                    }


                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }

            for (String input: masterList) {
                if (input.substring(0,4).equals("TAG:")) {
                    searched.get(searched.size() - 1).addTag(input.substring(4));
                }
                else {
                    searched.add(new Photo(Uri.parse(input)));
                }
            }
            if(true){}
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void write(){
        try {

            String str = "";
            new File(getFilesDir() + File.separator + "SearchRes.list").delete();
            FileOutputStream fileOutputStream = openFileOutput("SearchRes.list", MODE_PRIVATE);
            for (int i = 0; i < sList.size();i++) {
                ArrayList<String> tgs = new ArrayList<>();
                Photo u = sList.get(i);
                if (str.equals("")) {
                    str = u.getUri().toString();
                }
                else {
                    str = str + "\n" + u.getUri().toString();
                }
                for (Tag t : u.tags){
                    if (!tgs.contains(t.toString())) {
                        str = str + "\nTAG:" + t.toString();
                        tgs.add(t.toString());
                    }
                }
            }
            fileOutputStream.write(str.getBytes());

        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}

//need to check this
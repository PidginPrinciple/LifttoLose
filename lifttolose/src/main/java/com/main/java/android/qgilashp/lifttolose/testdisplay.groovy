package com.main.java.android.qgilashp.lifttolose;


import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter
import groovy.transform.CompileStatic;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pidgin
 * Date: 11/12/13
 * Time: 1:27 PM
 * To change this template use File | Settings | File Templates.
 */
@CompileStatic
public class testdisplay extends ActionBarActivity {

    private EditText testText;
    private com.cesarferreira.androidbootstrap.BootstrapButton addnoW;
    private TextView textView;
    //@InjectView(R.id.addnow) Button addnoW;
    String test = "Success";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.mainlayout);
        addnoW = (com.cesarferreira.androidbootstrap.BootstrapButton) findViewById(R.id.addnow);
        testText = (EditText) findViewById(R.id.addition);
        testText .setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);
        textView = (TextView) findViewById(R.id.textView2  );
        /* addnoW.setOnClickListener(new View.OnClickListener() {


     @Override
     public void onClick(View v) {
         //To change body of implemented methods use File | Settings | File Templates.
                 Toast.makeText(testdisplay.this, test, Toast.LENGTH_SHORT).show();
 kk
         }
     }); */
        //  Injector.inject(this);
        addnoW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                def filename = "teeeeeeest.csv";
                def content = testText.getText();
                def integer = content + 2;
                def list =  ["" + String.valueOf(integer) , "workout", "is", "cool", "and", "so", "are", "you", "haha"];
                String [] result = list.toArray(new String[list.size()]);
                createFile(filename, result);
                //textView.setText("The answer is " + readFile(filename));
                readFile(filename);
              //  Toast.makeText(testdisplay.this, test, Toast.LENGTH_SHORT).show();
                String name = "poop";
            }
        });


    }


    public void createFile(String fileName, String[] content){

        try
        {
            File root = new File(Environment.getExternalStorageDirectory(), "Noooootes");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, fileName);
            FileWriter writer = new FileWriter(gpxfile);
            CSVWriter csvwrite = new CSVWriter(writer);
            List<String[]> data = new ArrayList<String[]>();
            data.add(content);
            csvwrite.writeAll(data);
            csvwrite.flush();
            csvwrite.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void readFile(String fileName){
        //String line = null;
        String[] row = null;
        List<String[]> message = new ArrayList<String[]>();
        //BufferedReader buffer = null;
        FileReader reader;
        CSVReader csvreader;
        File gpxfile;
        try{
            File root = new File(Environment.getExternalStorageDirectory(), "Noooootes");

            if(!root.exists()){
               // Toast.makeText(this, "Folder does not Exist", Toast.LENGTH_SHORT).show();

            }
            gpxfile = new File(root, fileName);
            reader = new FileReader(gpxfile);
            //buffer = new BufferedReader(reader);
            csvreader = new CSVReader(reader);
            List content = csvreader.readAll();
            /*while((line=buffer.readLine())!=null){
               message += line;
            }  */
            for(Object object : content){
                row = (String[]) object;
                //String [] bum = new String[4];
                /*for(int i=0;i<bum.length;i++){
                       bum = row;
                } */
                for(int i = 0;i<row.length;i++){
                    if(i==0){textView.setText(row[0] + " ");  }else{
                        if(i%3==0){textView.append("\n");}else{textView.append(row[i] + " ");}  }
                }
                //message.add(bum);
            }
            try {
                csvreader.close();
            } catch (IOException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            //return message;
        }catch (IOException e){
            e.printStackTrace();

        }
        //return message;
    }

}

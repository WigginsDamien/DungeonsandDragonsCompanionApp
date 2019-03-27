package com.harrowedtale.dungeonsanddragonscompanionapp;


import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class CharacterSheet extends AppCompatActivity {
    Button nameButton;
    EditText characterName;
    TextView mText;
    String filename = "myfile";
    String fileContents = "Hello world!";
    FileOutputStream outputStream;
    //File file = new File(context.getFilesDir(), filename);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_sheet);

        nameButton = (Button)findViewById(R.id.nameButton);
        nameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                characterName   = (EditText)findViewById(R.id.characterName);
                mText = (TextView)findViewById(R.id.Title);
                mText.setText("Welcome "+ characterName.getText().toString()+"!");
                fileContents=characterName.getText().toString();
                writeToFile("Testia Wizard of Testing");
            }
        });
    }


    private void writeToFile(String data) {


        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.openFileOutput("character1.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
        /*
        File directory = new File(this.getFilesDir()+File.separator+"MyFolder");

        if(!directory.exists())
            directory.mkdir();

        File newFile = new File(directory, "myText.txt");

        if(!newFile.exists()){
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try  {
            FileOutputStream fOut = new FileOutputStream(newFile);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fOut);
            outputWriter.write("Test");
            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
        */
    }

    }


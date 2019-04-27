package com.harrowedtale.dungeonsanddragonscompanionapp;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CharacterSheet extends AppCompatActivity {
    Button nameButton;
    EditText characterName;
    TextView mText;
    String buffdude;
    String bufferdude;
    String fileContents = "Hello world!";
    FileOutputStream outputStream;
    String path="/data/data/com.harrowedtale.dungeonsanddragonscompanionapp/files";
    File[] Characters=new File(path).listFiles();
    String CharacterList[]= new String[Characters.length];
    //File file = new File(context.getFilesDir(), filename);

    //To view created files on android studio go to view -> Tool Windows -> Device File Explorer
    //Files are created in the data/data/com.harrowedtale.dungeonsanddragonscompanionapp/files



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_sheet);

        for (int i = 0; i < Characters.length; i++) {
            Log.d("Characters", "CharacterName:" + Characters[i].getName());
            CharacterList[i] = Characters[i].getName();
        }
        Spinner spinner = (Spinner) findViewById(R.id.characterSelectSpinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, CharacterList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mText= (TextView) findViewById(R.id.CharacterDisplay);
                buffdude=read_file(parent.getItemAtPosition(position).toString());
                mText.setText(buffdude);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });
    }


    private void writeToNewFile(String data) {
        try {
            //Context.MODE_PRIVATE creates a new file every time, this is what we want to do for the character creation wizard
            //for viewing/editing existing files we are going to need to use Context.MODE_APPEND
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.openFileOutput("character1.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

        //This is for if we want to store a character in its own unique folder

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

    private void writeToFile(String data) {
        try {
            //for viewing/editing existing files we are going to need to use Context.MODE_APPEND
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.openFileOutput("character1.txt", Context.MODE_APPEND));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public String read_file(String filename) {
        try {
            FileInputStream fis = this.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            return "";
        } catch (UnsupportedEncodingException e) {
            return "";
        } catch (IOException e) {
            return "";
        }
    }

   public String jsonify(String category, String input){
        String jsonString;
        jsonString="{ \"" +category +"\":" + "\"" +input+ "\"" + " }";
        return jsonString;
   }



}


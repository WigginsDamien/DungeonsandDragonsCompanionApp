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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class CustomItem extends AppCompatActivity {
    Button buttonName;
    EditText customItem;
    TextView mText;
    String buffdu;
    String fileContent = "Hello World";
    FileOutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_item);

        buttonName = findViewById(R.id.buttonName);
        buttonName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customItem = findViewById(R.id.customItem);

                fileContent = customItem.getText().toString();

                writeToNewFile(jsonfy("custom_item", fileContent));

                buffdu = read_file("customitem.txt");

                mText = findViewById(R.id.textView9);
                mText.setText("Welcome " + buffdu + "!");
            }
        });
    }

    private void writeToNewFile(String data) {
        try {
            //Context.MODE_PRIVATE creates a new file every time, this is what we want to do for the character creation wizard
            //for viewing/editing existing files we are going to need to use Context.MODE_APPEND
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.openFileOutput("customitem.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }


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

    public String jsonfy(String category, String input){
        String jsonString;
        jsonString="{ \"" +category +"\":" + "\"" +input+ "\"" + " }";
        return jsonString;
    }

}
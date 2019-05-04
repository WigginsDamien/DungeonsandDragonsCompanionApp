package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File f = new File("customItem");
        if(!f.exists()) {
            writeToFile("customItem", "");
        }
        mainGrid = (GridLayout) findViewById(R.id.menu_list);

        //Set event
        setSingleEvent(mainGrid);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setSingleEvent(GridLayout mainGrid){
        //Loop all child items of Main Grid
        for(int i = 0; i < mainGrid.getChildCount(); i++)
        {
            //All children are card views
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(finalI == 0)//For grimoire
                    {
                        Intent grimoire = new Intent(MainActivity.this, GrimoireActivity.class);
                        startActivity(grimoire);
                    }
                    if(finalI == 1)//For custom items
                    {
                        Intent customItems = new Intent(MainActivity.this, CustomItem.class);
                        startActivity(customItems);
                    }
                    else if(finalI == 2)//For the dice roller
                    {
                        Intent diceRoller = new Intent(MainActivity.this, DiceActivity.class);
                        startActivity(diceRoller);
                    }
                    else if(finalI == 3)//For the character sheets roller
                    {
                        Intent characterSheet = new Intent(MainActivity.this, CharacterSheet.class);
                        startActivity(characterSheet);
                    }
                    else if(finalI == 4)//For the new character creation wizard roller
                    {
                        Intent newUserWizard = new Intent(MainActivity.this, NewUserWizard.class);
                        startActivity(newUserWizard);
                    }
                }
            });
        }
    }
    private void writeToFile(String Name,String data) {
        try {
            //Context.MODE_PRIVATE creates a new file every time, this is what we want to do for the character creation wizard
            //for viewing/editing existing files we are going to need to use Context.MODE_APPEND
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.openFileOutput(Name, Context.MODE_APPEND));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

}

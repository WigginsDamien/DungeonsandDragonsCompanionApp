package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    String[] menuOptions = {
            "Grimoire", "Custom Items",
            "Dice Roller", "Character Sheets",
            "New User Wizard"
    };

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainGrid = (GridLayout) findViewById(R.id.menu_list);

        //Set event
        setSingleEvent(mainGrid);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        /*ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.mainmenu_list_item, menuOptions);

        GridView gridView = findViewById(R.id.menu_list);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)//For grimoire
                {
                    Intent grimoire = new Intent(MainActivity.this, GrimoireActivity.class);
                    startActivity(grimoire);
                }
                if(position == 1)//For custom items
                {
                    //TODO add in the correct custom items class
                    //Intent customItems = new Intent(MainActivity.this, customItems.class);
                    //startActivity(customItems);
                }
                else if(position == 2)//For the dice roller
                {
                    Intent diceRoller = new Intent(MainActivity.this, DiceActivity.class);
                    startActivity(diceRoller);
                }
                else if(position == 3)//For the character sheets roller
                {
                    Intent characterSheet = new Intent(MainActivity.this, CharacterSheet.class);
                    startActivity(characterSheet);
                }
                else if(position == 4)//For the new character creation wizard roller
                {
                    Intent newUserWizard = new Intent(MainActivity.this, NewUserWizard.class);
                    startActivity(newUserWizard);
                }
            }
        });*/
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
                        //TODO add in the correct custom items class
                        //Intent customItems = new Intent(MainActivity.this, customItems.class);
                        //startActivity(customItems);
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

}

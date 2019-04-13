package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    String[] menuOptions = {
            "Grimoire", "Custom Items",
            "Dice Roller", "Character Sheets",
            "New User Wizard"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.mainmenu_list_item, menuOptions);

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
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

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
            "Bestiary", "Items", "Spells",
            "Locations", "Classes", "Rules",
            "Feats", "Races", "Custom Items",
            "Dice Roller", "Feats", "Races",
            "Custom Items","Character Sheets",
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

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menuOptions);

        GridView gridView = findViewById(R.id.menu_list);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)//For bestiary
                {
                    Intent bestiary = new Intent(MainActivity.this, MonsterActivity.class);
                    startActivity(bestiary);
                }
                else if(position == 2)//For the spells
                {
                    Intent spells = new Intent(MainActivity.this, SpellActivity.class);
                    startActivity(spells);
                }
                else if(position == 9)//For the dice roller
                {
                    Intent diceRoller = new Intent(MainActivity.this, DiceActivity.class);
                    startActivity(diceRoller);
                }
                else if(position == 13)
                {
                    Intent characterSheet = new Intent(MainActivity.this, CharacterSheet.class);
                    startActivity(characterSheet);
                }
                else if(position == 14)
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

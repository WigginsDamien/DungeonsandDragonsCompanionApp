package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class GrimoireActivity extends AppCompatActivity {

    String[] menuOptions = {
            "Bestiary", "Items", "Spells",
            "Locations", "Classes",
            "Races", "Feats", "Rules"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grimoire);
        setTitle("Grimoire");

        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.mainmenu_list_item, menuOptions);
        GridView gridView = findViewById(R.id.menu_list);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)//For bestiary
                {
                    Intent bestiary = new Intent(GrimoireActivity.this, MonsterActivity.class);
                    startActivity(bestiary);
                }
                else if(position == 1)//For the Items
                {   //TODO Need to put in the correct item activity name
                    Intent items = new Intent(GrimoireActivity.this, ItemActivity.class);
                    startActivity(items);
                }
                else if(position == 2)//For the spells
                {
                    Intent spells = new Intent(GrimoireActivity.this, SpellActivity.class);
                    startActivity(spells);
                }
                else if(position == 3)//For the Locations
                {
                    Intent locations = new Intent(GrimoireActivity.this, LocationActivity.class);
                    startActivity(locations);
                }
                else if(position == 4)//For the classes
                {
                    Intent classes = new Intent(GrimoireActivity.this, PlayerClassActivity.class);
                    startActivity(classes);
                }
                else if(position == 5)//For the races
                {
                    //TODO Need to put in the correct races activity name
                    Intent races = new Intent(GrimoireActivity.this, RacesActivity.class);
                    startActivity(races);
                }
                else if(position == 6)//For the feats
                {
                    Intent feats = new Intent(GrimoireActivity.this, FeatsActivity.class);
                    startActivity(feats);
                }
                else if(position == 7)//For the rules
                {
                    Intent rules = new Intent(GrimoireActivity.this, RulesActivity.class);
                    startActivity(rules);
                }


            }
        });
    }
}


package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

public class RulesActivity extends AppCompatActivity {

    String[] menuOptions = {
            "Ability Scores", "Backgrounds", "Character Advancement",
            "Combat", "Conditions",
            "Damage Types", "Expenses", "Languages", "Mounts and Vehicles",
            "Movement", "Objects", "Resting", "Time", "Skills"
    };

    private String nextPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grimoire);

        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.mainmenu_list_item, menuOptions);
      //  getActionBar().setHomeButtonEnabled(true);
        GridView gridView = findViewById(R.id.menu_list);
        gridView.setAdapter(adapter);
        setTitle("Rulebook");
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)//Ability scores
                {
                    nextPage = menuOptions[0];
                    Intent nextIntent = new Intent(RulesActivity.this, RulesSectionActivity.class);
                    nextIntent.putExtra("Page", nextPage);
                    startActivity(nextIntent);
                }
                else if(position == 1)//Backgrounds
                {
                    nextPage = menuOptions[1];
                    Intent nextIntent = new Intent(RulesActivity.this, RulesSectionActivity.class);
                    nextIntent.putExtra("Page", "Backgrounds");
                    startActivity(nextIntent);
                }
                else if(position == 2)//So on
                {
                    nextPage = menuOptions[2];
                    Intent nextIntent = new Intent(RulesActivity.this, RulesSectionActivity.class);
                    nextIntent.putExtra("Page", "Character Advancement");
                    startActivity(nextIntent);
                }
                else if(position == 3)
                {
                    Toast.makeText(RulesActivity.this, menuOptions[3], Toast.LENGTH_SHORT);
                    nextPage = menuOptions[3];
                    Intent nextIntent = new Intent(RulesActivity.this, RulesSectionActivity.class);
                    nextIntent.putExtra("Page", nextPage);
                    startActivity(nextIntent);
                }
                else if(position == 4)
                {
                    nextPage = menuOptions[4];
                    Intent nextIntent = new Intent(RulesActivity.this, RulesSectionActivity.class);
                    nextIntent.putExtra("Page", nextPage);
                    startActivity(nextIntent);
                }
                else if(position == 5)
                {
                    nextPage = menuOptions[5];
                    Intent nextIntent = new Intent(RulesActivity.this, RulesSectionActivity.class);
                    nextIntent.putExtra("Page", nextPage);
                    startActivity(nextIntent);
                }
                else if(position == 6)
                {
                    nextPage = menuOptions[6];
                    Intent nextIntent = new Intent(RulesActivity.this, RulesSectionActivity.class);
                    nextIntent.putExtra("Page", nextPage);
                    startActivity(nextIntent);
                }
                else if(position == 7)
                {
                    nextPage = menuOptions[7];
                    Intent nextIntent = new Intent(RulesActivity.this, RulesSectionActivity.class);
                    nextIntent.putExtra("Page", nextPage);
                    startActivity(nextIntent);
                }
                else if(position == 8)
                {
                    nextPage = menuOptions[8];
                    Intent nextIntent = new Intent(RulesActivity.this, RulesSectionActivity.class);
                    nextIntent.putExtra("Page", nextPage);
                    startActivity(nextIntent);
                }
                else if(position == 9)
                {
                    nextPage = menuOptions[9];
                    Intent nextIntent = new Intent(RulesActivity.this, RulesSectionActivity.class);
                    nextIntent.putExtra("Page", nextPage);
                    startActivity(nextIntent);
                }
                else if(position == 10)
                {
                    nextPage = menuOptions[10];
                    Intent nextIntent = new Intent(RulesActivity.this, RulesSectionActivity.class);
                    nextIntent.putExtra("Page", nextPage);
                    startActivity(nextIntent);
                }
                else if(position == 11)
                {
                    nextPage = menuOptions[11];
                    Intent nextIntent = new Intent(RulesActivity.this, RulesSectionActivity.class);
                    nextIntent.putExtra("Page", nextPage);
                    startActivity(nextIntent);
                }
                else if(position == 12)
                {
                    nextPage = menuOptions[12];
                    Intent nextIntent = new Intent(RulesActivity.this, RulesSectionActivity.class);
                    nextIntent.putExtra("Page", nextPage);
                    startActivity(nextIntent);
                }
                else if(position == 13)
                {
                    nextPage = menuOptions[13];
                    Intent nextIntent = new Intent(RulesActivity.this, RulesSectionActivity.class);
                    nextIntent.putExtra("Page", nextPage);
                    startActivity(nextIntent);
                }
            }
        });
    }
}

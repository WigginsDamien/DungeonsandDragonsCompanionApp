package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FullMonsterActivity extends AppCompatActivity {

    public TextView monsterdata;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference Monster = db.collection("Monsters");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monster_full);
        monsterdata = findViewById(R.id.monsterData);
        Intent monster_display = getIntent();
        String Monster_name = monster_display.getStringExtra("Name");
        setTitle(Monster_name);//Sets page title to the respective monster
        DocumentReference monster = db.collection("Monsters").document(Monster_name);
        Toast.makeText(this, Monster_name, Toast.LENGTH_SHORT).show();
        monster.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                String monsterinfo = "Stats:\n\n";
                DocumentSnapshot snap = task.getResult();
                String temp;
                Number temp_int;
                ArrayList<Map<String,String>> test = (ArrayList<Map<String, String>>) snap.get("actions");
                temp_int = (Number) snap.get("hitPoints");
                monsterinfo += ("Hitpoints: " + temp_int + "\n");
                temp = (String) snap.get("hitDice");
                monsterinfo += ("Hit Dice: " + temp + "\n");
                temp_int = (Number) snap.get("armorClass");
                monsterinfo += ("Armor Class: " + String.valueOf(temp_int) + "\n");
                temp_int = (Number) snap.get("challengeRating");
                monsterinfo += ("Challenge Rating: " + String.valueOf(temp_int) + "\n");
                temp_int = (Number) snap.get("constitution");
                monsterinfo += ("Constitution: " + String.valueOf(temp_int) + "\n");
                temp_int = (Number) snap.get("dexterity");
                monsterinfo += ("Dexterity: " + String.valueOf(temp_int) + "\n");
                temp_int = (Number) snap.get("intelligence");
                monsterinfo += ("Intelligence: " + String.valueOf(temp_int) + "\n");
                temp_int = (Number) snap.get("strength");
                monsterinfo += ("Strength: " + String.valueOf(temp_int) + "\n");
                temp_int = (Number) snap.get("wisdom");
                monsterinfo += ("Wisdom: " + String.valueOf(temp_int) + "\n");
                temp = (String) snap.get("alignment");
                monsterinfo += ("Alignment: " + temp + "\n");
                temp = (String) snap.get("conditionImmunities");
                monsterinfo += ("Condition Immunities: " + temp + "\n");
                temp = (String) snap.get("damageImmunities");
                monsterinfo += ("Damage Immunities: " + temp + "\n");
                temp = (String) snap.get("damageResistances");
                monsterinfo += ("Damage Resistances: " + temp + "\n");
                temp = (String) snap.get("damageVulnerabilities");
                monsterinfo += ("Vulnerable to: " + temp + "\n");
                temp = (String) snap.get("size");
                monsterinfo += ("Size: " + temp + "\n");
                temp = (String) snap.get("speed");
                monsterinfo += ("Speed: " + temp + "\n");
                temp = (String) snap.get("type");
                monsterinfo += ("Type: " + temp + "\n");
                temp = (String) snap.get("subtype");
                monsterinfo += ("Subtype: " + temp + "\n");
                temp = (String) snap.get("languages");
                monsterinfo += ("Languages: " + temp + "\n");
                temp = (String) snap.get("senses");
                monsterinfo += ("Senses: " + temp + "\n\n");
                //String monster_alignment = monster_for_page.getAlignment();
                monsterinfo += ("Actions:\n\n");
                for(Map<String, String> num : test)
                {
                    monsterinfo += num.get("name");
                    monsterinfo += ":\n";
                    monsterinfo += num.get("description");
                    monsterinfo += "\n\n";
                }

                test = (ArrayList<Map<String,String>>) snap.get("legendaryActions");
                monsterinfo += ("Legendary Actions:\n\n");
                for(Map<String, String> num : test)
                {
                    monsterinfo += num.get("name");
                    monsterinfo += ":\n";
                    monsterinfo += num.get("description");
                    monsterinfo += "\n\n";
                }
                //test = (ArrayList<Map<String, String>>) snap.get("specialAbilities");
                monsterinfo += ("Special Abilities:\n\n");
                for(Map<String, String> num : test)
                {
                    monsterinfo += num.get("name");
                    monsterinfo += ":\n";
                    monsterinfo += num.get("description");
                    monsterinfo += "\n\n";
                }
                monsterdata.setText(monsterinfo);
            }
        });

    }

}


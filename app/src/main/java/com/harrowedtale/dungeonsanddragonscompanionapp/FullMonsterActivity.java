package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import org.w3c.dom.Text;
import java.util.ArrayList;
import java.util.Map;

public class FullMonsterActivity extends AppCompatActivity {

    public TextView monsterdata;
    public TextView intelligence;
    public TextView strength;
    private TextView charisma;
    private TextView dexterity;
    private TextView wisdom;
    private TextView constitution;
    private TextView monsterActions;
    private TextView monsterLegendaryActions;
    private TextView monsterAbilities;
    private TextView monsterLegendaryActionTitle;
    private TextView monsterAbilityTitle;
    private TextView monsterTitles;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference Monster = db.collection("Monsters");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monster_full);
        monsterLegendaryActionTitle = findViewById(R.id.monsterLegendaryActionsTitle);
        monsterAbilityTitle = findViewById(R.id.monsterAbilityTitle);
        monsterdata = findViewById(R.id.monsterData);
        intelligence = findViewById(R.id.monsterInt);
        strength = findViewById(R.id.monsterStr);
        charisma = findViewById(R.id.monsterCha);
        dexterity = findViewById(R.id.monsterDex);
        wisdom = findViewById(R.id.monsterWis);
        constitution = findViewById(R.id.monsterCon);
        monsterActions = findViewById(R.id.monsterActionData);
        monsterLegendaryActions = findViewById(R.id.monsterLegendaryActions);
        monsterAbilities = findViewById(R.id.monsterAbility);
        monsterTitles = findViewById(R.id.monsterTitles);
        Intent monster_display = getIntent();
        String Monster_name = monster_display.getStringExtra("Name");//get monster name from previous activity
        setTitle(Monster_name);//Sets page title to the respective monster
        DocumentReference monster = db.collection("Monsters").document(Monster_name);//open document in the database
 //       Toast.makeText(this, Monster_name, Toast.LENGTH_SHORT).show();
        monster.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                String monsterinfo = "";
                String monstertitles = "";
                DocumentSnapshot snap = task.getResult();//assigns the document to snap
                String temp;
                Number temp_int;
                ArrayList<Map<String,String>> test = (ArrayList<Map<String, String>>) snap.get("actions");
                if(snap.contains("hitPoints")) {
                    temp_int = (Number) snap.get("hitPoints");
                    monsterinfo = ("Hitpoints: " +"\n");
                    monstertitles += temp_int + "\n";

                }
                if(snap.contains("hitDice")) {
                    temp = (String) snap.get("hitDice");
                    monsterinfo += ("Hit Dice: " + "\n");
                    monstertitles += temp + "\n";
                }
                if(snap.contains("armorClass")) {
                    temp_int = (Number) snap.get("armorClass");
                    monsterinfo += ("Armor Class: " + "\n");
                    monstertitles += temp_int + "\n";
                }
                if(snap.contains("challengeRating")) {
                    temp_int = (Number) snap.get("challengeRating");
                    monsterinfo += ("Challenge Rating: " + "\n");
                    monstertitles += temp_int + "\n";
                }
                if(snap.contains("constitution")) {
                    temp_int = (Number) snap.get("constitution");
                    constitution.setText(String.valueOf(temp_int));
                }
                if(snap.contains("dexterity")) {
                    temp_int = (Number) snap.get("dexterity");
                    dexterity.setText(String.valueOf(temp_int));
                }
                if(snap.contains("intelligence")) {
                    temp_int = (Number) snap.get("intelligence");
                    intelligence.setText(String.valueOf(temp_int));
                }
                if(snap.contains("strength")) {
                    temp_int = (Number) snap.get("strength");
                    strength.setText(String.valueOf(temp_int));
                }
                if(snap.contains("wisdom")) {
                    temp_int = (Number) snap.get("wisdom");
                    wisdom.setText(String.valueOf(temp_int));
                }
                if(snap.contains("charisma")) {
                    charisma.setText(String.valueOf(snap.get("charisma")));
                }
                if(snap.contains("alignment")) {
                    temp = (String) snap.get("alignment");
                    monsterinfo += ("Alignment: " + "\n");
                    monstertitles += temp + "\n";
                }
                if(snap.get("conditionImmunities") != "") {
                    temp = (String) snap.get("conditionImmunities");
                    monsterinfo += ("Condition Immunities: " + "\n");
                    monstertitles += temp + "\n";
                }
                if(snap.get("damageImmunities") != "") {
                    temp = (String) snap.get("damageImmunities");
                    monsterinfo += ("Damage Immunities: " +  "\n");
                    monstertitles += temp + "\n";
                }
                if(snap.get("damageResistances") != "") {
                    temp = (String) snap.get("damageResistances");
                    monsterinfo += ("Damage Resistances: " + "\n");
                    monstertitles += temp + "\n";
                }
                if(snap.get("damageVulnerabilities") != "") {
                    temp = (String) snap.get("damageVulnerabilities");
                    monsterinfo += ("Vulnerable to: " + "\n");
                    monstertitles += temp + "\n";
                }
                if(snap.get("size") != "") {
                    temp = (String) snap.get("size");
                    monsterinfo += ("Size: " +  "\n");
                    monstertitles += temp + "\n";
                }
                if(snap.get("speed") != "") {
                    temp = (String) snap.get("speed");
                    monsterinfo += ("Speed: " +  "\n");
                    monstertitles += temp + "\n";
                }
                if(snap.get("type") != "") {
                    temp = (String) snap.get("type");
                    monsterinfo += ("Type: " + "\n");
                    monstertitles += temp + "\n";
                }
                if(snap.get("subtype") != "") {
                    temp = (String) snap.get("subtype");
                    monsterinfo += ("Subtype: " + "\n");
                    monstertitles += temp + "\n";
                }
                if(snap.get("languages") != "") {
                    temp = (String) snap.get("languages");
                    monsterinfo += ("Languages: " + "\n");
                    monstertitles += temp + "\n";
                }
                if(snap.get("senses") != "") {
                    temp = (String) snap.get("senses");
                    monsterinfo += ("Senses: " + "\n\n");
                    monstertitles += temp + "\n\n";
                }
                //String monster_alignment = monster_for_page.getAlignment();
                String monster_actions = "";
                if(snap.get("actions") != "") {
                    for (Map<String, String> num : test) {
                        monster_actions += num.get("name");
                        monster_actions += ":\n";
                        monster_actions += num.get("description");
                        monster_actions += "\n\n";
                    }
                }
                monsterActions.setText(monster_actions);
                if(snap.get("legendaryActions") != "") {
                    test = (ArrayList<Map<String, String>>) snap.get("legendaryActions");
                    if(test.isEmpty()) {

                    }
                    else {
                        String monsterlegendaryactions = "";
                        String title = "Legendary Actions";
                        monsterLegendaryActionTitle.setText(title);
                        for (Map<String, String> num : test) {
                            monsterlegendaryactions += num.get("name");
                            monsterlegendaryactions += ":\n";
                            monsterlegendaryactions += num.get("description");
                            monsterlegendaryactions += "\n\n";
                        }
                        monsterLegendaryActions.setText(monsterlegendaryactions);
                    }
                }
                if(snap.get("specialAbilities") != "") {
                    test = (ArrayList<Map<String, String>>) snap.get("specialAbilities");
                    if(test.isEmpty()) {
                    }
                    else {
                        String title = ("Special Abilities");
                        String monsterabilities = "";
                        monsterAbilityTitle.setText(title);
                        for (Map<String, String> num : test) {
                            monsterabilities += num.get("name");
                            monsterabilities += ":\n";
                            monsterabilities += num.get("description");
                            monsterabilities += "\n\n";
                        }
                        monsterAbilities.setText(monsterabilities);
                    }
                }
                monsterdata.setText(monsterinfo);
                monsterTitles.setText(monstertitles);
            }
        });

    }

}


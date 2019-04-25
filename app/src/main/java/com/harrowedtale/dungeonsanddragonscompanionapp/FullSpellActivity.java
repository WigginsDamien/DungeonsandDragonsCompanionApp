package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

public class FullSpellActivity extends AppCompatActivity {

    private TextView spellConcentration;
    private TextView spellCastTime;
    private TextView spellComponents;
    private TextView spellDescription;
    private TextView spellRange;
    private TextView spellLevel;
    private TextView spellDuration;
    private TextView spellSchool;
    private TextView spellPage;
    private TextView spellClasses;
    private TextView spellRitual;
    private TextView spellHigherLevel;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference Spell = db.collection("Spells");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spellbook_full);
        spellConcentration = findViewById(R.id.spellConcentration);
        spellCastTime = findViewById(R.id.spellCastTime);
        spellComponents = findViewById(R.id.spellComponents);
        spellDescription = findViewById(R.id.spellDescription);
        spellRange = findViewById(R.id.spellRange);
        spellLevel = findViewById(R.id.spellLevel);
        spellDuration = findViewById(R.id.spellDuration);
        spellSchool = findViewById(R.id.spellSchool);
        spellPage = findViewById(R.id.spellPage);
        spellClasses = findViewById(R.id.spellClasses);
        spellRitual = findViewById(R.id.spellRitual);
        spellHigherLevel = findViewById(R.id.spellHigherLevel);
        Intent spell_display = getIntent();
        String Spell_name = spell_display.getStringExtra("Name");
        setTitle(Spell_name);//Sets page title to the respective monster
        DocumentReference spell = db.collection("Spells").document(Spell_name);
      //  Toast.makeText(this, Spell_name, Toast.LENGTH_SHORT).show();
        spell.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                String spellInfo = "";
                DocumentSnapshot snap = task.getResult();
                String temp;
                temp = (String) snap.get("level");
                spellInfo += (temp);
                spellLevel.setText(temp);
                temp = (String) snap.get("school");
                spellInfo += (" " + temp + "\n");
                temp = (String) snap.get("castingTime");
                spellCastTime.setText(temp);
                temp = (String) snap.get("range");
                spellRange.setText(temp);
                temp = (String) snap.get("components");
                /*if(snap.get("material") != "")
                {
                   temp += "\n" + snap.get("material");
                }*/
                spellComponents.setText(temp);
                temp = (String) snap.get("description");
                spellDescription.setText(temp);
                temp = (String) snap.get("duration");
                spellDuration.setText(temp);
                temp = (String) snap.get("concentration");
                spellConcentration.setText(temp);
                temp = (String) snap.get("classes");
                spellClasses.setText(temp);
                temp = (String) snap.get("school");
                spellSchool.setText(temp);
                if(!(snap.get("higherLevel").equals(""))) {
                    spellHigherLevel.setText((String)snap.get("higherLevel"));
                }
                else
                {
                    spellHigherLevel.setText("Using this spell at a higher slot has no increased benefit.");
                }
                temp = (String) snap.get("page") + "\nIndex: " + snap.get("index");
                spellPage.setText(temp);
                spellRitual.setText((String)snap.get("ritual"));
            }
        });

    }

}


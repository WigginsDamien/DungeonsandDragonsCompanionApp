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

public class FullSpellActivity extends AppCompatActivity {

    public TextView spellData;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference Spell = db.collection("Spells");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spellbook_full);
        spellData = findViewById(R.id.spellData);
        Intent spell_display = getIntent();
        String Spell_name = spell_display.getStringExtra("Name");
        setTitle(Spell_name);//Sets page title to the respective monster
        DocumentReference spell = db.collection("Spells").document(Spell_name);
        Toast.makeText(this, Spell_name, Toast.LENGTH_SHORT).show();
        spell.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                String spellInfo = "";
                DocumentSnapshot snap = task.getResult();
                String temp;
                temp = (String) snap.get("level");
                spellInfo += (temp);
                temp = (String) snap.get("school");
                spellInfo += (" " + temp + "\n");
                temp = (String) snap.get("castingTime");
                spellInfo += ("Casting Time: " + temp + "\n");
                temp = (String) snap.get("range");
                spellInfo += ("Range: " + temp + "\n");
                temp = (String) snap.get("components");
                spellInfo += ("Components: " + temp + "\n");

                spellData.setText(spellInfo);
            }
        });

    }

}


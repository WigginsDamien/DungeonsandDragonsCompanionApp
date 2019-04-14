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
import java.util.Map;

public class FullSpellbookActivity extends AppCompatActivity{
    public TextView spelldata;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference Spells = db.collection("Spells");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spellbook_full);
        Intent spell_display = getIntent();
        String Spell_name = spell_display.getStringExtra("Name");
        DocumentReference spell = db.collection("Spells").document(Spell_name);
        Toast.makeText(this,Spell_name, Toast.LENGTH_SHORT).show();
        spell.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot doc = task.getResult();
                spelldata = findViewById(R.id.spellData);
                // monsterdata.setText(doc);
                Map<String, Object> monster = doc.getData();
                // monsterdata.setText(monster);
            }
        });

    }
    public class DocData{
        private String castingTime;
        private String classes;
        private String components;
        private String concentration;
        private String description;
        private String duration;
        private String higherLevel;
        private String level;
        private String material;
        private String range;
        private String school;
        private String ritual;

        public DocData(){

        }
    }
}

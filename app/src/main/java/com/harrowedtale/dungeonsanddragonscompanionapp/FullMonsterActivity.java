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
        Intent monster_display = getIntent();
        String Monster_name = monster_display.getStringExtra("Name");
        DocumentReference monster = db.collection("Monsters").document(Monster_name);
        Toast.makeText(this, Monster_name, Toast.LENGTH_SHORT).show();
        monster.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot doc = task.getResult();
                monsterdata = findViewById(R.id.monsterData);
               // monsterdata.setText(doc);
                Map<String, Object> monster = doc.getData();
               // monsterdata.setText(monster);
            }
        });

    }

    public class DocData{
        private List<Map<String,String>> actions;
        private List<Map<String,String>> legendaryActions;
        private String alignment;
        private String armorClass;
        private String challengeRating;
        private String conditionImmunities;
        private String constitution;
        private String damageImmunities;


        public DocData(){

        }
    }
}

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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class FullRaceActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private TextView race_data;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.race_fullinformation);
        race_data = findViewById(R.id.raceData);
        Intent race = getIntent();
        String raceName = race.getStringExtra("Name");
        setTitle(raceName);
        DocumentReference race_ref = db.collection("Races").document(raceName);
        race_ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                String race_info = "";
                DocumentSnapshot race = task.getResult();
                Map<String, String> basic = (Map<String, String>) race.get("Basic");
                for(Map.Entry<String, String> basic_data : basic.entrySet()) {
                    race_info += basic_data.getKey() + " ";
                    race_info += basic_data.getValue() + "\n";
                }
                if(race.contains("Subrace")) {
                    race_info += "\n\nSubrace\n";
                    Map<String, String> subrace = (Map<String, String>) race.get("Subrace");
                    for(Map.Entry<String, String> basic_data : subrace.entrySet()) {
                        race_info += basic_data.getKey() + " ";
                        race_info += basic_data.getValue() + "\n";
                    }
                }
                if(race.contains("Tinker")) {
                    race_info += "Tinker\n";
                    Map<String, String> tinker = (Map<String, String>) race.get("Tinker");
                    for(Map.Entry<String, String> basic_data : tinker.entrySet()) {
                        race_info += basic_data.getKey() + " ";
                        race_info += basic_data.getValue() + "\n";
                    }
                }
                race_data.setText(race_info);
            }
        });
        Toast.makeText(this, raceName, Toast.LENGTH_SHORT).show();
    }
}

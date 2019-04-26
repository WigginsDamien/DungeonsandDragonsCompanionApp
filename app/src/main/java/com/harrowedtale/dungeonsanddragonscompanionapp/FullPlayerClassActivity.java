package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class FullPlayerClassActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private TextView playerclassData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.race_fullinformation);
        playerclassData = findViewById(R.id.raceData);
        Intent classIntent = getIntent();
        String className = classIntent.getStringExtra("Name");
        setTitle(className);
        DocumentReference classDoc = db.collection("Classes").document(className);

        classDoc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot className = task.getResult();
                Map<String, Object> classdata = className.getData();
                String class_data = "";
                for(String key : classdata.keySet()) {
                    class_data += classdata.get(key).toString();
                }
                playerclassData.setText(class_data);
            }
        });
    }
}

package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FullItemActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private TextView itemData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent itemToDisplay = getIntent();
        String item = itemToDisplay.getStringExtra("Name");
        setContentView(R.layout.item_fullinformation);
        itemData = findViewById(R.id.itemData);
        setTitle(item);
        db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Equipment").document(item);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    String itemdata = "";
                    DocumentSnapshot document = task.getResult();
                 /*   if(document.get("name") != "") {
                        String name = (String) document.get("name");
                        itemdata += "Name: " + name + "\n";
                    }*/
                    if(document.contains("description")) {
                        String description = (String) document.get("description");
                        itemdata += "Description: " + description + "\n";
                    }
                    if(document.contains("cost")) {
                        String cost = (String) document.get("cost");
                        itemdata += "Cost: " + cost + "\n";
                    }
                    if(document.contains("equipmentCategory")) {
                        String equipmentCategory = (String) document.get("equipmentCategory");
                        itemdata += "Equipment Category: " + equipmentCategory + "\n";
                    }
                    if(document.contains("categoryRange")) {
                        String equiprange = (String) document.get("categoryRange");
                        itemdata += "Category Range: " + equiprange + "\n";
                    }
                    if(document.contains("weight")) {
                        Number weight = (Number) document.get("weight");
                        itemdata += "Weight: " + String.valueOf(weight) + "\n";
                    }
                    if(document.contains("index")) {
                        Number index = (Number) document.get("index");
                        itemdata += "Index: " + index + "\n";
                    }
                    if(document.contains("speed")) {
                        String speed = (String) document.get("speed");
                        itemdata += "Speed: " + speed + "\n";
                    }
                    if(document.contains("strMinimum")) {
                        Number strmin = (Number) document.get("strMinimum");
                        itemdata += "Strength Requirement: " + strmin + "\n";
                    }
                    if(document.contains("stealthDisadvantage")) {
                        if(document.get("stealthDisadvantage").equals(false)) {

                        }
                        else {
                            itemdata += "Using this item gives Disadvantage to Stealth\n";
                        }
                    }
                    if(document.contains("armorClass")) {
                        Map<String, Object> armorClass = (Map<String, Object>) document.get("armorClass");
                        itemdata += "Armor Class\n\n";
                        itemdata += "Base: " + armorClass.get("base") + "\n";
                        if(armorClass.get("dexBonus").equals(false)) {
                            itemdata += "No Dex Bonus" + "\n";
                        }
                        else {
                            itemdata += "Max Dex Bonus: " + armorClass.get("maxBonus") + "\n";
                        }
                    }
                    if(document.contains("damage")) {
                        Map<String, Object> damage = (Map<String, Object>) document.get("damage");
                        itemdata += "Damage\n\n";
                        itemdata += damage.get("diceCount") + "d" + damage.get("diceValue") + " " + damage.get("damageType") + "\n";
                    }
                    if(document.contains("2h_damage")) {
                        Map<String, Object> damage = (Map<String, Object>) document.get("2h_damage");
                        itemdata += "Damage\n\n";
                        itemdata += damage.get("diceCount") + "d" + damage.get("diceValue") + " ";
                        itemdata += "Type: " + damage.get("damageType") + "\n";
                    }
                    if(document.contains("properties")) {
                        ArrayList<String> properties = (ArrayList<String>) document.get("properties");
                        itemdata += "Properties" + "\n\n";
                        for(String itemProperties : properties) {
                            itemdata += itemProperties + ",";
                        }
                    }
                    itemData.setText(itemdata);
                }
            }
        });


    }
}

package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FullItemActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private LinearLayout itemLinLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent itemToDisplay = getIntent();
        String item = itemToDisplay.getStringExtra("Name");
        setContentView(R.layout.item_fullinformation);
        setTitle(item);
        itemLinLayout = findViewById(R.id.itemLinLayout);
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
                    /*if(document.contains("description")) {
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
                    }*/
                    Map<String, Object> item_data = document.getData();
                    item_data.remove("name");
                    item_data.remove("damage");
                    item_data.remove("2h_damage");
                    item_data.remove("armorClass");
                    item_data.remove("properties");
                    String item_header;
                    for(Map.Entry<String, Object> iteminfo : item_data.entrySet()) {
                        item_header = iteminfo.getKey();
                        Object item_description = iteminfo.getValue();
                        if(item_header.equals("stealthDisadvantage")) {
                            if(item_description.equals(false)) {
                                continue;
                            }
                            else
                            {
                                item_header = "Stealth Disadvantage";
                                item_description = "Using this item gives Disadvantage to Stealth";
                            }
                        }
                        if(item_header.equals("cost")) {
                            item_header = "Cost";
                        }
                        if(item_header.equals("index")) {
                            item_header = "Index";
                        }
                        if(item_header.equals("equipmentCategory")) {
                            item_header = "Category of Equipment";
                        }
                        if(item_header.equals("weight")) {
                            item_header = "Weight";
                        }
                        if(item_header.equals("speed")) {
                            item_header = "Speed";
                        }
                        if(item_header.equals("description")) {
                            item_header = "Description";
                        }
                        if(item_header.equals("strMinimum")) {
                            item_header = "Minimum Strength Requirement";
                        }
                        if(item_header.equals("categoryRange")) {
                            item_header = "Weapon Type";
                        }
                        TextView textView1 = new TextView(itemLinLayout.getContext());
                        textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                        textView1.setText(item_header);
                        textView1.setTypeface(Typeface.DEFAULT_BOLD);
                        textView1.setTextSize(16);
                        textView1.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                        itemLinLayout.addView(textView1);
                        // Add textview 2
                        TextView textView2 = new TextView(itemLinLayout.getContext());
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                        textView2.setLayoutParams(layoutParams);
                        textView2.setText(item_description.toString());
                        itemLinLayout.addView(textView2);
                    }
                   if(document.contains("armorClass")) {
                       Map<String, Object> armorClass = (Map<String, Object>) document.get("armorClass");
                           TextView textView1 = new TextView(itemLinLayout.getContext());
                           textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                   LinearLayout.LayoutParams.WRAP_CONTENT));
                           textView1.setText("Armor Class");
                           textView1.setTypeface(Typeface.DEFAULT_BOLD);
                           textView1.setTextSize(16);
                           textView1.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                           itemLinLayout.addView(textView1);
                           // Add textview 2
                           TextView textView2 = new TextView(itemLinLayout.getContext());
                           LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                   LinearLayout.LayoutParams.WRAP_CONTENT);
                           layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                           textView2.setLayoutParams(layoutParams);
                           itemdata += "Base: " + armorClass.get("base") + "\n";
                           if (armorClass.get("dexBonus").equals(false)) {
                               itemdata += "No Dex Bonus" + "\n";
                           } else {
                               itemdata += "Max Dex Bonus: " + armorClass.get("maxBonus") + "\n";
                           }
                           textView2.setText(itemdata);
                       itemLinLayout.addView(textView2);

                   }

                    if(document.contains("damage")) {
                        Map<String, Object> damage = (Map<String, Object>) document.get("damage");
                        TextView textView1 = new TextView(itemLinLayout.getContext());
                        textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                        textView1.setText("Damage");
                        textView1.setTypeface(Typeface.DEFAULT_BOLD);
                        textView1.setTextSize(16);
                        textView1.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                        itemLinLayout.addView(textView1);
                        // Add textview 2
                        TextView textView2 = new TextView(itemLinLayout.getContext());
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                        textView2.setLayoutParams(layoutParams);
                        itemdata = damage.get("diceCount") + "d" + damage.get("diceValue") + " " + damage.get("damageType");
                        textView2.setText(itemdata);
                        itemLinLayout.addView(textView2);
                    }
                    if(document.contains("2h_damage")) {
                        Map<String, Object> damage = (Map<String, Object>) document.get("2h_damage");
                        itemdata = damage.get("diceCount") + "d" + damage.get("diceValue") + " " +damage.get("damageType");
                        TextView textView1 = new TextView(itemLinLayout.getContext());
                        textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                        textView1.setText("2H Damage");
                        textView1.setTypeface(Typeface.DEFAULT_BOLD);
                        textView1.setTextSize(16);
                        textView1.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                        itemLinLayout.addView(textView1);
                        // Add textview 2
                        TextView textView2 = new TextView(itemLinLayout.getContext());
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                        textView2.setLayoutParams(layoutParams);
                        textView2.setText(itemdata.toString());
                        itemLinLayout.addView(textView2);
                    }
                    if(document.contains("properties")) {
                        ArrayList<String> properties = (ArrayList<String>) document.get("properties");
                        TextView textView1 = new TextView(itemLinLayout.getContext());
                        textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                        textView1.setText("Properties");
                        textView1.setTypeface(Typeface.DEFAULT_BOLD);
                        textView1.setTextSize(16);
                        textView1.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                        itemLinLayout.addView(textView1);
                        // Add textview 2
                        TextView textView2 = new TextView(itemLinLayout.getContext());
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                        textView2.setLayoutParams(layoutParams);
                        itemdata = "";
                        for(String itemProperties : properties) {
                            itemdata += itemProperties + "" + "\n";
                        }
                        textView2.setText(itemdata);
                        itemLinLayout.addView(textView2);
                    }


                }
            }
        });


    }
}

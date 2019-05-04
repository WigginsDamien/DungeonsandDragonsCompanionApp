package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

public class FullPlayerClassActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private LinearLayout ClassLayout;
    private TableLayout ClassTable;
    private TableLayout FeatureTable;
    private TableLayout SpellTable;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.race_fullinformation);
        ClassLayout = findViewById(R.id.raceLinLayout);
        TextView classtableHeader = findViewById(R.id.spelltableHeader);
        classtableHeader.setTextSize(20);
        classtableHeader.setTypeface(Typeface.DEFAULT_BOLD);
        classtableHeader.setPadding(10,0,10,0);
        ClassTable = findViewById(R.id.classTable);
        FeatureTable = findViewById(R.id.featureTable);
        SpellTable = findViewById(R.id.spellTable);
        final TableRow.LayoutParams TableParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0);
        final TableRow.LayoutParams TableRowParams = new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableParams.setMargins(10, 0, 0, 0); // (left, top, right, bottom)
        Intent classIntent = getIntent();
        String className = classIntent.getStringExtra("Name");//get Class Name
        if(!((className.equals("Fighter") || className.equals("Monk") || className.equals("Rogue") || className.equals("Warlock") || className.equals("Barbarian")))) {
            classtableHeader.setText("Spellcasting\n__________________");
        }
        setTitle(className);
        final DocumentReference classDoc = db.collection("Classes").document(className);//create document reference of class name

        classDoc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {//make sure doc is actually assigned
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot className = task.getResult();
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                Map<String, Object> classdata = className.getData();
                String class_data = "";
                Object class_info;
                ArrayList<Map<String, String>> classFeatures = (ArrayList<Map<String, String>>) classdata.get("classFeatures");
                classdata.remove("classFeatures");
                ArrayList<String> equipment = (ArrayList<String>) classdata.get("equipment");
                classdata.remove("equipment");
                Map<String, String> hitPoints = (Map<String, String>) classdata.get("hitPoints");
                classdata.remove("hitPoints");
                Map<String, String> proficiencies = (Map<String, String>) classdata.get("proficiencies");
                classdata.remove("proficiencies");
                ArrayList<Object> subclass = new ArrayList<>();
                if(classdata.containsKey("subclass")) {
                    subclass = (ArrayList<Object>) classdata.get("subclass");
                }
                else {
                    subclass = (ArrayList<Object>) classdata.get("subClass");
                }
                classdata.remove("subclass");
                classdata.remove("subClass");
                Map<String, Object> classtable = (Map<String, Object>) classdata.get("table");
                classdata.remove("table");
                String classDescription = (String)classdata.get("description");
                classdata.remove("description");



                class_data = "Hitpoints";
                TextView textView7 = new TextView(ClassLayout.getContext());
                layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                textView7.setLayoutParams(layoutParams);
                textView7.setText(class_data);
                textView7.setTextSize(20);
                textView7.setTextColor(Color.parseColor("#6d1b1b"));
                textView7.setTypeface(Typeface.DEFAULT_BOLD);
                ClassLayout.addView(textView7);
                for(Map.Entry<String,String> i : hitPoints.entrySet()) {
                    class_data = i.getKey();
                //    class_data = class_data.toUpperCase();
                    TextView textView6 = new TextView(ClassLayout.getContext());
                    if(class_data.equals("firstLevel")) {
                        class_data = "At Level 1";
                    }
                    if(class_data.equals("hitDice")) {
                        class_data = "Hit Dice";
                    }
                    if(class_data.equals("higherLevels")) {
                        class_data = "At Higher Levels";
                    }
                    layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                    textView6.setLayoutParams(layoutParams);
                    textView6.setText(class_data);
                    textView6.setTextSize(16);
                 //   textView6.setTextColor(Color.parseColor("#6d1b1b"));
                    textView6.setTypeface(Typeface.DEFAULT_BOLD);
                    ClassLayout.addView(textView6);
                    class_data = i.getValue();
                    TextView textView4 = new TextView(ClassLayout.getContext());
                    layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                    textView4.setLayoutParams(layoutParams);
                    textView4.setText(class_data);
                    ClassLayout.addView(textView4);
                }
                class_data = "Starting Proficiences";
                TextView textView8 = new TextView(ClassLayout.getContext());
                layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                textView8.setLayoutParams(layoutParams);
                textView8.setText(class_data);
                textView8.setTextSize(20);
                textView8.setTextColor(Color.parseColor("#6d1b1b"));
                textView8.setTypeface(Typeface.DEFAULT_BOLD);
                ClassLayout.addView(textView8);
                for(Map.Entry<String,String> i : proficiencies.entrySet()) {
                    class_data = i.getKey();
                    if(class_data.equals("armor")) {
                        class_data = "Armor";
                    }
                    if(class_data.equals("savingThrows")) {
                        class_data = "Saving Throws";
                    }
                    if(class_data.equals("skills")) {
                        class_data = "Skills";
                    }
                    if(class_data.equals("tools")) {
                        class_data = "Tools";
                    }
                    if(class_data.equals("weapons")) {
                        class_data = "Weapons";
                    }
                    TextView textView6 = new TextView(ClassLayout.getContext());
                    layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                    textView6.setLayoutParams(layoutParams);
                    textView6.setText(class_data);
                    textView6.setTextSize(16);
                   // textView6.setTextColor(Color.parseColor("#6d1b1b"));
                    textView6.setTypeface(Typeface.DEFAULT_BOLD);
                    ClassLayout.addView(textView6);
                    class_data = i.getValue();
                    TextView textView4 = new TextView(ClassLayout.getContext());
                    layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                    textView4.setLayoutParams(layoutParams);
                    textView4.setText(class_data);
                    ClassLayout.addView(textView4);
                }

                class_data = "Starting Equipment";
                TextView textViewequipment = new TextView(ClassLayout.getContext());
                layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                textViewequipment.setLayoutParams(layoutParams);
                textViewequipment.setText(class_data);
                textViewequipment.setTextSize(20);
                textViewequipment.setTextColor(Color.parseColor("#6d1b1b"));
                textViewequipment.setTypeface(Typeface.DEFAULT_BOLD);
                ClassLayout.addView(textViewequipment);
                for(String i : equipment) {
                    class_data = i;
                    TextView textView5 = new TextView(ClassLayout.getContext());
                    layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                    textView5.setLayoutParams(layoutParams);
                    textView5.setText(class_data);
                    ClassLayout.addView(textView5);
                }

                String cantripData = "";
                String classCountData = "";
                String damageData = "";
                String featureData = "";
                String levelData = "";
                String proficiencyData = "";
                String spellsData = "";
                ArrayList<String> cantrips = (ArrayList<String>) classtable.get("cantrips");
                ArrayList<String> classCount = (ArrayList<String>) classtable.get("classCount");
                ArrayList<String> damage = (ArrayList<String>) classtable.get("damage");
                ArrayList<String> features = (ArrayList<String>) classtable.get("features");
                ArrayList<String> level = (ArrayList<String>) classtable.get("level");
                ArrayList<String> proficiency = (ArrayList<String>) classtable.get("proficiency");
                ArrayList<String> spells = (ArrayList<String>) classtable.get("spells");

                if(!cantrips.contains("")) {
                    for(String i : cantrips) {
                        cantripData += i + "\n";
                    }
                }
                if(!classCount.contains("")) {
                    for(String i : classCount) {
                        classCountData += i + "\n";
                    }
                }
                if(!damage.contains("")) {
                    for(String i : damage) {
                        damageData += i + "\n";
                    }
                }
                if(!features.contains("")) {
                    for(String i: features) {
                        featureData += i + "\n";
                    }
                }
                if(!level.contains("")) {
                    for(String i : level) {
                        levelData += i + "\n";
                    }
                }
                if(!proficiency.contains("")) {
                    for(String i: proficiency) {
                        proficiencyData += i + "\n";
                    }
                }
                if(!spells.contains("")) {
                    for(String i: spells) {
                        spellsData += i + "\n";
                    }
                }

                String[] tableStats = {levelData, proficiencyData, cantripData, classCountData, damageData, spellsData};
                TableRow row = new TableRow(ClassTable.getContext());
                TableRow headerRow = new TableRow(ClassTable.getContext());
                row.setLayoutParams(TableRowParams);
                headerRow.setLayoutParams(TableRowParams);
                String[] tableStatsheader = {"LVL", "Bonus", "Cantrips", "Count Avail", "Damage", "#Spells Known"};

                for(int tablenum = 0; tablenum < tableStats.length; tablenum++) {
                    if(!(tableStats[tablenum].equals(""))) {
                        TextView tableData = new TextView(ClassTable.getContext());
                        TextView tableHeader = new TextView(ClassTable.getContext());
                        tableData.setText(tableStats[tablenum]);
                        tableData.setLayoutParams(TableParams);
                        tableHeader.setText(tableStatsheader[tablenum]);
                        tableHeader.setLayoutParams(TableParams);
                        tableHeader.setTypeface(Typeface.DEFAULT_BOLD);
                        row.addView(tableData);
                        headerRow.addView(tableHeader);
                    }
                }
                ClassTable.addView(headerRow);
                ClassTable.addView(row);
                TableRow featureinfo = new TableRow(FeatureTable.getContext());
                featureinfo.setLayoutParams(TableRowParams);
                TableRow featureHeader = new TableRow(FeatureTable.getContext());
                featureHeader.setLayoutParams(TableRowParams);
                if(!features.equals("")) {
                    TextView tableData = new TextView(ClassTable.getContext());
                    TextView tableHeader = new TextView(ClassTable.getContext());
                    TextView levelHeader = new TextView(ClassTable.getContext());
                    TextView featureHead = new TextView(ClassTable.getContext());
                    featureHead.setLayoutParams(TableParams);
                    featureHead.setTypeface(Typeface.DEFAULT_BOLD);
                    featureHead.setTextSize(16);
                    featureHead.setText("Feature");
                    tableData.setText(featureData);
                    tableData.setLayoutParams(TableParams);
                    tableHeader.setText("LVL");
                    tableHeader.setTextSize(16);
                    tableHeader.setTypeface(Typeface.DEFAULT_BOLD);
                    tableHeader.setLayoutParams(TableParams);
                    levelHeader.setLayoutParams(TableParams);
                    levelHeader.setText(levelData);
                    featureinfo.addView(levelHeader);
                    featureinfo.addView(tableData);
                    featureHeader.addView(tableHeader);
                    featureHeader.addView(featureHead);
                    if(classDoc.getId().equals("Warlock")) {
                        String levelinfo;
                    ArrayList<String> spellSlots = (ArrayList<String>) classtable.get("spellSlots");
                    TextView spellHeader = new TextView(ClassTable.getContext());
                    TextView spellDescription = new TextView(ClassTable.getContext());
                    spellHeader.setLayoutParams(TableParams);
                    spellDescription.setLayoutParams(TableParams);
                    levelinfo = "";
                    for (String j : spellSlots) {
                        levelinfo += j + "\n";
                    }
                    spellHeader.setTypeface(Typeface.DEFAULT_BOLD);
                    spellHeader.setText("Spell Slots");
                    featureHeader.addView(spellHeader);
                    spellDescription.setText(levelinfo);
                    featureinfo.addView(spellDescription);
                    }
                }
                FeatureTable.addView(featureHeader);
                FeatureTable.addView(featureinfo);
                String levelheader = "";
                String levelinfo = "";
                TableRow SpellHeader = new TableRow(SpellTable.getContext());
                TableRow SpellDescription = new TableRow(SpellTable.getContext());
                SpellHeader.setLayoutParams(TableRowParams);
                SpellDescription.setLayoutParams(TableRowParams);
                if(classDoc.getId().equals("Warlock")) {
            /*        ArrayList<String> spellSlots = (ArrayList<String>) classtable.get("spellSlots");
                    TextView spellHeader = new TextView(ClassTable.getContext());
                    TextView spellDescription = new TextView(ClassTable.getContext());
                    spellHeader.setLayoutParams(TableParams);
                    spellDescription.setLayoutParams(TableParams);
                    levelinfo = "";
                    for (String j : spellSlots) {
                        levelinfo += j + "\n";
                    }
                    spellHeader.setTypeface(Typeface.DEFAULT_BOLD);
             //       spellHeader.setText(spellslot.getKey());
                    SpellHeader.addView(spellHeader);
                    spellDescription.setText(levelinfo);
                    SpellDescription.addView(spellDescription);*/
                }
                else {
                    Map<String, ArrayList<String>> spellSlots = (Map<String, ArrayList<String>>) classtable.get("spellSlots");
                    for (Map.Entry<String, ArrayList<String>> spellslot : spellSlots.entrySet()) {
                        if (!(spellslot.getValue().equals(""))) {
                            TextView spellHeader = new TextView(ClassTable.getContext());
                            TextView spellDescription = new TextView(ClassTable.getContext());
                            spellHeader.setLayoutParams(TableParams);
                            spellDescription.setLayoutParams(TableParams);
                            levelinfo = "";
                            ArrayList<String> info = spellslot.getValue();
                            for (String j : info) {
                                levelinfo += j + "\n";
                            }
                            spellHeader.setTypeface(Typeface.DEFAULT_BOLD);
                            spellHeader.setText(spellslot.getKey());
                            SpellHeader.addView(spellHeader);
                            spellDescription.setText(levelinfo);
                            SpellDescription.addView(spellDescription);
                        }
                    }
                    SpellTable.addView(SpellHeader);
                    SpellTable.addView(SpellDescription);
                }
                for(String key : classdata.keySet()) {
                    class_data = key;
                    if(class_data.equals("startingGold")) {
                        class_data = "Starting Gold";
                    }
                    if(class_data.equals("suggestedAbilities")) {
                        class_data = "Suggested Abilities";
                    }
                    class_info = classdata.get(key);
                    TextView textView1 = new TextView(ClassLayout.getContext());
                    textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    textView1.setText(class_data);
                    textView1.setTypeface(Typeface.DEFAULT_BOLD);
                    textView1.setTextColor(Color.parseColor("#6d1b1b"));
                    textView1.setTextSize(20);
                    textView1.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                    ClassLayout.addView(textView1);
                    // Add textview 2

                    TextView textView2 = new TextView(ClassLayout.getContext());
                    layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                    textView2.setLayoutParams(layoutParams);
                    textView2.setText(class_info.toString());
                    ClassLayout.addView(textView2);
                }
                    for(int i = 0; i < subclass.size(); i++) {//get all subclass features added to layout
                        if(i == 0 || i ==1 ) {
                            class_data = (String) subclass.get(i);
                        }
                        else {
                            Map<String, Object> subclass_features = (Map<String,Object>) subclass.get(i);
                            class_data = (String) subclass_features.get("name");
                            TextView textView3 = new TextView(ClassLayout.getContext());
                            layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                            textView3.setLayoutParams(layoutParams);
                            textView3.setText(class_data);
                            textView3.setTextSize(16);
                            textView3.setTypeface(Typeface.DEFAULT_BOLD);
                            // textView3.setTextColor(Color.parseColor("#6d1b1b"));
                            ClassLayout.addView(textView3);
                            class_data = (String) subclass_features.get("description");
                            TextView textView4 = new TextView(ClassLayout.getContext());
                            layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                            textView4.setLayoutParams(layoutParams);
                            textView4.setText(class_data);
                            ClassLayout.addView(textView4);
                            ArrayList<String> subclassfeatures = (ArrayList<String>) subclass_features.get("features");
                            for(int o = 0; o < subclassfeatures.size(); o++) {
                                if(o % 2 == 0) {
                                    class_data = (String) subclassfeatures.get(o);
                                    TextView textViewfeatureheader = new TextView(ClassLayout.getContext());
                                    layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                                    textViewfeatureheader.setLayoutParams(layoutParams);
                                    textViewfeatureheader.setText(class_data);
                                    textViewfeatureheader.setTextSize(16);
                                    //   textViewfeatureheader.setTextColor(Color.parseColor("#6d1b1b"));
                                    ClassLayout.addView(textViewfeatureheader);
                                }
                                else {
                                    class_data = (String) subclassfeatures.get(o);
                                    TextView textViewfeaturedesscription = new TextView(ClassLayout.getContext());
                                    layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                                    textViewfeaturedesscription.setLayoutParams(layoutParams);
                                    textViewfeaturedesscription.setText(class_data);
                                    ClassLayout.addView(textViewfeaturedesscription);
                                }
                            }
                        }
                        TextView textView4 = new TextView(ClassLayout.getContext());
                        layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                        textView4.setLayoutParams(layoutParams);
                        textView4.setText(class_data);
                        ClassLayout.addView(textView4);
                    }
                /*if(!(subClass.isEmpty())) {
                    subclass = subClass;
                    for(int i = 0; i < subclass.size(); i++) {//get all subclass features added to layout
                        if(i == 0 || i ==1 ) {
                            class_data = (String) subclass.get(i);
                        }
                        else {
                            Map<String, Object> subclass_features = (Map<String,Object>) subclass.get(i);
                            class_data = (String) subclass_features.get("name");
                            TextView textView3 = new TextView(ClassLayout.getContext());
                            layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                            textView3.setLayoutParams(layoutParams);
                            textView3.setText(class_data);
                            textView3.setTextSize(16);
                            textView3.setTypeface(Typeface.DEFAULT_BOLD);
                            // textView3.setTextColor(Color.parseColor("#6d1b1b"));
                            ClassLayout.addView(textView3);
                            class_data = (String) subclass_features.get("description");
                            TextView textView4 = new TextView(ClassLayout.getContext());
                            layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                            textView4.setLayoutParams(layoutParams);
                            textView4.setText(class_data);
                            ClassLayout.addView(textView4);
                            ArrayList<String> subclassfeatures = (ArrayList<String>) subclass_features.get("features");
                            for(int o = 0; o < subclassfeatures.size(); o++) {
                                if(o % 2 == 0) {
                                    class_data = (String) subclassfeatures.get(o);
                                    TextView textViewfeatureheader = new TextView(ClassLayout.getContext());
                                    layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                                    textViewfeatureheader.setLayoutParams(layoutParams);
                                    textViewfeatureheader.setText(class_data);
                                    textViewfeatureheader.setTextSize(16);
                                    //   textViewfeatureheader.setTextColor(Color.parseColor("#6d1b1b"));
                                    ClassLayout.addView(textViewfeatureheader);
                                }
                                else {
                                    class_data = (String) subclassfeatures.get(o);
                                    TextView textViewfeaturedesscription = new TextView(ClassLayout.getContext());
                                    layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                                    textViewfeaturedesscription.setLayoutParams(layoutParams);
                                    textViewfeaturedesscription.setText(class_data);
                                    ClassLayout.addView(textViewfeaturedesscription);
                                }
                            }
                        }

                        TextView textView4 = new TextView(ClassLayout.getContext());
                        layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                        textView4.setLayoutParams(layoutParams);
                        textView4.setText(class_data);
                        ClassLayout.addView(textView4);
                    }
                }*/
                TextView textView0 = new TextView(ClassLayout.getContext());
                textView0.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                textView0.setText(classDescription);
                textView0.setTextSize(20);
                textView0.setTextColor(Color.parseColor("#6d1b1b"));
                textView0.setTypeface(Typeface.DEFAULT_BOLD);
                textView0.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                ClassLayout.addView(textView0);

                for(Map<String,String> i : classFeatures) {//get all class features added to layout
                    class_data = i.get("name");
                    TextView textView3 = new TextView(ClassLayout.getContext());
                    layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                    textView3.setLayoutParams(layoutParams);
                    textView3.setText(class_data);
                    textView3.setTextSize(16);
                    textView3.setTypeface(Typeface.DEFAULT_BOLD);
                  //  textView3.setTextColor(Color.parseColor("#6d1b1b"));
                    ClassLayout.addView(textView3);
                    class_data = i.get("description");
                    TextView textView4 = new TextView(ClassLayout.getContext());
                    layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                    textView4.setLayoutParams(layoutParams);
                    textView4.setText(class_data);
                    ClassLayout.addView(textView4);
                }

            }
        });
    }
}

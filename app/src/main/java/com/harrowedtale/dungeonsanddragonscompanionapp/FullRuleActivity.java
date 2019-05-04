package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.content.Intent;
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

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class FullRuleActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private LinearLayout RuleLayout;
    private TableLayout Table1;
    private TableLayout Table2;
    private TableLayout Table3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.race_fullinformation);
        RuleLayout = findViewById(R.id.raceLinLayout);
        Table1 = findViewById(R.id.spellTable);
        Table2 = findViewById(R.id.featureTable);
        Table2 = findViewById(R.id.classTable);
        final TableRow.LayoutParams TableParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0);
        final TableRow.LayoutParams TableRowParams = new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableParams.setMargins(10, 0, 0, 0); // (left, top, right, bottom)
        Intent classIntent = getIntent();
        String className = classIntent.getStringExtra("Name");
        String ruleCollection = classIntent.getStringExtra("SectionName");
        setTitle(className);
        DocumentReference classDoc = db.collection(ruleCollection).document(className);

        classDoc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot className = task.getResult();
                Map<String, Object> ruledata = className.getData();
                ArrayList<Map<String,ArrayList<String>>> tables = new ArrayList<>();
                String rule_header;
                Object rule_description;
                if(ruledata.containsKey("table")) {
                    tables.add((Map<String, ArrayList<String>>)ruledata.get("table"));
                    ruledata.remove("table");
                }
                if(ruledata.containsKey("Servicestable")) {
                    tables.add((Map<String, ArrayList<String>>)ruledata.get("Servicestable"));
                    ruledata.remove("Servicestable");
                }
                if(ruledata.containsKey("Foodtable")) {
                    tables.add((Map<String, ArrayList<String>>)ruledata.get("Foodtable"));
                    ruledata.remove("Foodtable");
                }
                if(ruledata.containsKey("itemtable")) {
                    tables.add((Map<String, ArrayList<String>>)ruledata.get("itemtable"));
                    ruledata.remove("itemtable");
                }
                if(ruledata.containsKey("Watertable")) {
                    tables.add((Map<String,ArrayList<String>>)ruledata.get("Watertable"));
                    ruledata.remove("Watertable");
                }
                if(ruledata.containsKey("ACtable")) {
                    tables.add((Map<String,ArrayList<String>>)ruledata.get("ACtable"));
                    ruledata.remove("ACtable");
                }
                if(ruledata.containsKey("HPtable")) {
                    tables.add((Map<String, ArrayList<String>>)ruledata.get("HPtable"));
                    ruledata.remove("HPtable");
                }
                if(ruledata.containsKey("Animaltable")) {
                    tables.add((Map<String, ArrayList<String>>)ruledata.get("Animaltable"));
                    ruledata.remove("Animaltable");
                }
                if(ruledata.containsKey("description")) {
                    rule_header = "Description";
                TextView textView = new TextView(RuleLayout.getContext());
                textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                //rule_header = rule_header.toUpperCase();
                textView.setTypeface(Typeface.DEFAULT_BOLD);
                textView.setTextSize(20);
                textView.setText(rule_header);
                textView.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                RuleLayout.addView(textView);
                    rule_header = (String) ruledata.get("description");
                    TextView textView0 = new TextView(RuleLayout.getContext());
                    textView0.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    textView0.setText(rule_header);
                    textView0.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                    RuleLayout.addView(textView0);
                }
                ruledata.remove("description");
                if (ruledata.containsKey("More Detail")) {
                    Map<String, String> keydata = (Map<String, String>) ruledata.get("More Detail");
                    for(Map.Entry<String, String> data : keydata.entrySet()) {
                        TextView textView1 = new TextView(RuleLayout.getContext());
                        textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                        // rule_header = rule_header.toUpperCase();
                        textView1.setText(data.getKey());
                        textView1.setTypeface(Typeface.DEFAULT_BOLD);
                        textView1.setTextSize(16);
                        textView1.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                        RuleLayout.addView(textView1);
                        // Add textview 2
                        TextView textView2 = new TextView(RuleLayout.getContext());
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                        textView2.setLayoutParams(layoutParams);
                        textView2.setText(data.getValue());
                        RuleLayout.addView(textView2);
                    }
                    ruledata.remove("More Detail");
                }
                if (ruledata.containsKey("Contests in Combat")) {
                    Map<String, String> keydata = (Map<String, String>) ruledata.get("Contests in Combat");
                    String description = keydata.get("descirption");
                    TextView textViewdescriptheader = new TextView(RuleLayout.getContext());
                    textViewdescriptheader.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    // rule_header = rule_header.toUpperCase();
                    String descriptionheader = "Contests in Combat";
                    textViewdescriptheader.setText(descriptionheader);
                    textViewdescriptheader.setTypeface(Typeface.DEFAULT_BOLD);
                    textViewdescriptheader.setTextSize(20);
                    textViewdescriptheader.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                    RuleLayout.addView(textViewdescriptheader);
                    // Add textview 2
                    TextView textViewdescriptbody = new TextView(RuleLayout.getContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                    textViewdescriptbody.setLayoutParams(layoutParams);
                    textViewdescriptbody.setText(description);
                    RuleLayout.addView(textViewdescriptbody);
                    keydata.remove("descirption");
                    for(Map.Entry<String, String> data : keydata.entrySet()) {
                        TextView textView1 = new TextView(RuleLayout.getContext());
                        textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                        // rule_header = rule_header.toUpperCase();
                        textView1.setText(data.getKey());
                        textView1.setTypeface(Typeface.DEFAULT_BOLD);
                        textView1.setTextSize(16);
                        textView1.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                        RuleLayout.addView(textView1);
                        // Add textview 2
                        TextView textView2 = new TextView(RuleLayout.getContext());
                        layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                        textView2.setLayoutParams(layoutParams);
                        textView2.setText(data.getValue());
                        RuleLayout.addView(textView2);
                    }
                    ruledata.remove("Contests in Combat");
                }
                if (ruledata.containsKey("Melee Attacks")) {
                    Map<String, String> keydata = (Map<String, String>) ruledata.get("Melee Attacks");
                    String description = keydata.get("description");
                    TextView textViewdescriptheader = new TextView(RuleLayout.getContext());
                    textViewdescriptheader.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    // rule_header = rule_header.toUpperCase();
                    String descriptionheader = "Melee Attacks";
                    textViewdescriptheader.setText(descriptionheader);
                    textViewdescriptheader.setTypeface(Typeface.DEFAULT_BOLD);
                    textViewdescriptheader.setTextSize(20);
                    textViewdescriptheader.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                    RuleLayout.addView(textViewdescriptheader);
                    // Add textview 2
                    TextView textViewdescriptbody = new TextView(RuleLayout.getContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                    textViewdescriptbody.setLayoutParams(layoutParams);
                    textViewdescriptbody.setText(description);
                    RuleLayout.addView(textViewdescriptbody);
                    keydata.remove("description");
                    for(Map.Entry<String, String> data : keydata.entrySet()) {
                        TextView textView1 = new TextView(RuleLayout.getContext());
                        textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                        // rule_header = rule_header.toUpperCase();
                        textView1.setText(data.getKey());
                        textView1.setTypeface(Typeface.DEFAULT_BOLD);
                        textView1.setTextSize(16);
                        textView1.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                        RuleLayout.addView(textView1);
                        // Add textview 2
                        TextView textView2 = new TextView(RuleLayout.getContext());
                        layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                        textView2.setLayoutParams(layoutParams);
                        textView2.setText(data.getValue());
                        RuleLayout.addView(textView2);
                    }
                    ruledata.remove("Melee Attacks");
                }
                if (ruledata.containsKey("Ranged Attacks")) {
                    Map<String, String> keydata = (Map<String, String>) ruledata.get("Ranged Attacks");
                    String description = keydata.get("description");
                    TextView textViewdescriptheader = new TextView(RuleLayout.getContext());
                    textViewdescriptheader.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    // rule_header = rule_header.toUpperCase();
                    String descriptionheader = "Ranged Attacks";
                    textViewdescriptheader.setText(descriptionheader);
                    textViewdescriptheader.setTypeface(Typeface.DEFAULT_BOLD);
                    textViewdescriptheader.setTextSize(20);
                    textViewdescriptheader.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                    RuleLayout.addView(textViewdescriptheader);
                    // Add textview 2
                    TextView textViewdescriptbody = new TextView(RuleLayout.getContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                    textViewdescriptbody.setLayoutParams(layoutParams);
                    textViewdescriptbody.setText(description);
                    RuleLayout.addView(textViewdescriptbody);
                    keydata.remove("description");
                    for(Map.Entry<String, String> data : keydata.entrySet()) {
                        TextView textView1 = new TextView(RuleLayout.getContext());
                        textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                        // rule_header = rule_header.toUpperCase();
                        textView1.setText(data.getKey());
                        textView1.setTypeface(Typeface.DEFAULT_BOLD);
                        textView1.setTextSize(16);
                        textView1.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                        RuleLayout.addView(textView1);
                        // Add textview 2
                        TextView textView2 = new TextView(RuleLayout.getContext());
                        layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                        textView2.setLayoutParams(layoutParams);
                        textView2.setText(data.getValue());
                        RuleLayout.addView(textView2);
                    }
                    ruledata.remove("Ranged Attacks");
                }
                if (ruledata.containsKey("Breaking Up Your Move")) {
                    Map<String, String> keydata = (Map<String, String>) ruledata.get("Breaking Up Your Move");
                    String description = keydata.get("description");
                    TextView textViewdescriptheader = new TextView(RuleLayout.getContext());
                    textViewdescriptheader.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    // rule_header = rule_header.toUpperCase();
                    String descriptionheader = "Breaking Up Your Move";
                    textViewdescriptheader.setText(descriptionheader);
                    textViewdescriptheader.setTypeface(Typeface.DEFAULT_BOLD);
                    textViewdescriptheader.setTextSize(20);
                    textViewdescriptheader.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                    RuleLayout.addView(textViewdescriptheader);
                    // Add textview 2
                    TextView textViewdescriptbody = new TextView(RuleLayout.getContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                    textViewdescriptbody.setLayoutParams(layoutParams);
                    textViewdescriptbody.setText(description);
                    RuleLayout.addView(textViewdescriptbody);
                    keydata.remove("description");
                    for(Map.Entry<String, String> data : keydata.entrySet()) {
                        TextView textView1 = new TextView(RuleLayout.getContext());
                        textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                        // rule_header = rule_header.toUpperCase();
                        textView1.setText(data.getKey());
                        textView1.setTypeface(Typeface.DEFAULT_BOLD);
                        textView1.setTextSize(16);
                        textView1.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                        RuleLayout.addView(textView1);
                        // Add textview 2
                        TextView textView2 = new TextView(RuleLayout.getContext());
                        layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                        textView2.setLayoutParams(layoutParams);
                        textView2.setText(data.getValue());
                        RuleLayout.addView(textView2);
                    }
                    ruledata.remove("Breaking Up Your Move");
                }
                if (ruledata.containsKey("Creature Size")) {
                    Map<String, String> keydata = (Map<String, String>) ruledata.get("Creature Size");
                    String description = keydata.get("description");
                    TextView textViewdescriptheader = new TextView(RuleLayout.getContext());
                    textViewdescriptheader.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    // rule_header = rule_header.toUpperCase();
                    String descriptionheader = "Creature Size";
                    textViewdescriptheader.setText(descriptionheader);
                    textViewdescriptheader.setTypeface(Typeface.DEFAULT_BOLD);
                    textViewdescriptheader.setTextSize(20);
                    textViewdescriptheader.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                    RuleLayout.addView(textViewdescriptheader);
                    // Add textview 2
                    TextView textViewdescriptbody = new TextView(RuleLayout.getContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                    textViewdescriptbody.setLayoutParams(layoutParams);
                    textViewdescriptbody.setText(description);
                    RuleLayout.addView(textViewdescriptbody);
                    keydata.remove("description");
                    for(Map.Entry<String, String> data : keydata.entrySet()) {
                        TextView textView1 = new TextView(RuleLayout.getContext());
                        textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                        // rule_header = rule_header.toUpperCase();
                        textView1.setText(data.getKey());
                        textView1.setTypeface(Typeface.DEFAULT_BOLD);
                        textView1.setTextSize(16);
                        textView1.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                        RuleLayout.addView(textView1);
                        // Add textview 2
                        TextView textView2 = new TextView(RuleLayout.getContext());
                        layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                        textView2.setLayoutParams(layoutParams);
                        textView2.setText(data.getValue());
                        RuleLayout.addView(textView2);
                    }
                    ruledata.remove("Creature Size");
                }
                if (ruledata.containsKey("Downtime Activities")) {
                    Map<String, String> keydata = (Map<String, String>) ruledata.get("Downtime Activities");
                    String description = keydata.get("description");
                    TextView textViewdescriptheader = new TextView(RuleLayout.getContext());
                    textViewdescriptheader.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    // rule_header = rule_header.toUpperCase();
                    String descriptionheader = "Downtime Activities";
                    textViewdescriptheader.setText(descriptionheader);
                    textViewdescriptheader.setTypeface(Typeface.DEFAULT_BOLD);
                    textViewdescriptheader.setTextSize(20);
                    textViewdescriptheader.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                    RuleLayout.addView(textViewdescriptheader);
                    // Add textview 2
                    TextView textViewdescriptbody = new TextView(RuleLayout.getContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                    textViewdescriptbody.setLayoutParams(layoutParams);
                    textViewdescriptbody.setText(description);
                    RuleLayout.addView(textViewdescriptbody);
                    keydata.remove("description");
                    for(Map.Entry<String, String> data : keydata.entrySet()) {
                        TextView textView1 = new TextView(RuleLayout.getContext());
                        textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                        // rule_header = rule_header.toUpperCase();
                        textView1.setText(data.getKey());
                        textView1.setTypeface(Typeface.DEFAULT_BOLD);
                        textView1.setTextSize(16);
                        textView1.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                        RuleLayout.addView(textView1);
                        // Add textview 2
                        TextView textView2 = new TextView(RuleLayout.getContext());
                        layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                        textView2.setLayoutParams(layoutParams);
                        textView2.setText(data.getValue());
                        RuleLayout.addView(textView2);
                    }
                    ruledata.remove("Downtime Activities");
                }
                if (ruledata.containsKey("Attack Rolls")) {
                    Map<String, Object> keydata = (Map<String, Object>) ruledata.get("Attack Rolls");
                    Map<String, String> modifier = (Map<String, String>) keydata.get("Modifiers to the Roll");
                    String description = (String) keydata.get("description");
                    TextView textViewdescriptheader = new TextView(RuleLayout.getContext());
                    textViewdescriptheader.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    // rule_header = rule_header.toUpperCase();
                    String descriptionheader = "Attack Rolls";
                    textViewdescriptheader.setText(descriptionheader);
                    textViewdescriptheader.setTypeface(Typeface.DEFAULT_BOLD);
                    textViewdescriptheader.setTextSize(20);
                    textViewdescriptheader.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                    RuleLayout.addView(textViewdescriptheader);
                    // Add textview 2
                    TextView textViewdescriptbody = new TextView(RuleLayout.getContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                    textViewdescriptbody.setLayoutParams(layoutParams);
                    textViewdescriptbody.setText(description);
                    RuleLayout.addView(textViewdescriptbody);
                    String subheader = "Modifiers to the Roll";
                    TextView subHeader = new TextView(RuleLayout.getContext());
                    TextView subDesc = new TextView(RuleLayout.getContext());
                    subHeader.setLayoutParams(layoutParams);
                    subHeader.setTypeface(Typeface.DEFAULT_BOLD);
                    subHeader.setTextSize(16);
                   // subHeader.setPadding(15,20,20,20);
                    subDesc.setLayoutParams(layoutParams);
                   // subDesc.setPadding(15,20,20,20);
                    subHeader.setText(subheader);
                    subDesc.setText(modifier.get("description"));
                    RuleLayout.addView(subHeader);
                    RuleLayout.addView(subDesc);
                    keydata.remove("description");
                    keydata.remove("Modifiers to the Roll");
                    modifier.remove("description");
                    for(Map.Entry<String, String> modifierdata : modifier.entrySet()) {
                        TextView dataHeader = new TextView(RuleLayout.getContext());
                        TextView dataBody = new TextView(RuleLayout.getContext());
                        dataHeader.setTypeface(Typeface.DEFAULT_BOLD);
                        dataHeader.setTextSize(16);
                        dataHeader.setText(modifierdata.getKey());
                        dataHeader.setLayoutParams(layoutParams);
                        dataBody.setLayoutParams(layoutParams);
                        dataBody.setText(modifierdata.getValue());
                        RuleLayout.addView(dataHeader);
                        RuleLayout.addView(dataBody);
                    }
                    for(Map.Entry<String, Object> data : keydata.entrySet()) {
                        TextView textView1 = new TextView(RuleLayout.getContext());
                        textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                        // rule_header = rule_header.toUpperCase();
                        textView1.setText(data.getKey());
                        textView1.setTypeface(Typeface.DEFAULT_BOLD);
                        textView1.setTextSize(16);
                        textView1.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                        RuleLayout.addView(textView1);
                        // Add textview 2
                        TextView textView2 = new TextView(RuleLayout.getContext());
                        layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                        textView2.setLayoutParams(layoutParams);
                        textView2.setText(data.getValue().toString());
                        RuleLayout.addView(textView2);
                    }
                    ruledata.remove("Attack Rolls");
                }
                if (ruledata.containsKey("Damage and Healing")) {
                    Map<String, Object> keydata = (Map<String, Object>) ruledata.get("Damage and Healing");
                    Map<String, String> modifier = (Map<String, String>) keydata.get("Dropping to 0 Hit Points");
                    String description = (String) keydata.get("description");
                    TextView textViewdescriptheader = new TextView(RuleLayout.getContext());
                    textViewdescriptheader.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    // rule_header = rule_header.toUpperCase();
                    String descriptionheader = "Damage and Healing";
                    textViewdescriptheader.setText(descriptionheader);
                    textViewdescriptheader.setTypeface(Typeface.DEFAULT_BOLD);
                    textViewdescriptheader.setTextSize(20);
                    textViewdescriptheader.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                    RuleLayout.addView(textViewdescriptheader);
                    // Add textview 2
                    TextView textViewdescriptbody = new TextView(RuleLayout.getContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                    textViewdescriptbody.setLayoutParams(layoutParams);
                    textViewdescriptbody.setText(description);
                    RuleLayout.addView(textViewdescriptbody);
                    String subheader = "Dropping to 0 Hit Points";
                    TextView subHeader = new TextView(RuleLayout.getContext());
                    TextView subDesc = new TextView(RuleLayout.getContext());
                    subHeader.setLayoutParams(layoutParams);
                    subHeader.setTypeface(Typeface.DEFAULT_BOLD);
                    subHeader.setTextSize(16);
                   // subHeader.setPadding(15,20,20,20);
                    subDesc.setLayoutParams(layoutParams);
                  //  subDesc.setPadding(15,20,20,20);
                    subHeader.setText(subheader);
                    subDesc.setText(modifier.get("description"));
                    RuleLayout.addView(subHeader);
                    RuleLayout.addView(subDesc);
                    keydata.remove("description");
                    keydata.remove("Dropping to 0 Hit Points");
                    modifier.remove("description");
                    for(Map.Entry<String, String> modifierdata : modifier.entrySet()) {
                        TextView dataHeader = new TextView(RuleLayout.getContext());
                        TextView dataBody = new TextView(RuleLayout.getContext());
                        dataHeader.setTypeface(Typeface.DEFAULT_BOLD);
                        dataHeader.setTextSize(16);
                        dataHeader.setLayoutParams(layoutParams);
                        dataBody.setLayoutParams(layoutParams);
                        dataHeader.setText(modifierdata.getKey());
                        dataBody.setText(modifierdata.getValue());
                        RuleLayout.addView(dataHeader);
                        RuleLayout.addView(dataBody);

                    }
                    for(Map.Entry<String, Object> data : keydata.entrySet()) {
                        TextView textView1 = new TextView(RuleLayout.getContext());
                        textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                        // rule_header = rule_header.toUpperCase();
                        textView1.setText(data.getKey());
                        textView1.setTypeface(Typeface.DEFAULT_BOLD);
                        textView1.setTextSize(16);
                        textView1.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                        RuleLayout.addView(textView1);
                        // Add textview 2
                        TextView textView2 = new TextView(RuleLayout.getContext());
                        layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                        textView2.setLayoutParams(layoutParams);
                        textView2.setText(data.getValue().toString());
                        RuleLayout.addView(textView2);
                    }
                    ruledata.remove("Damage and Healing");
                }
                if (ruledata.containsKey("Special Types of Movement")) {
                    Map<String, Object> keydata = (Map<String, Object>) ruledata.get("Special Types of Movement");
                    Map<String, String> modifier = (Map<String, String>) keydata.get("Jumping");
                    Map<String, Object> speedData = (Map<String, Object>) ruledata.get("Speed");
                    Map<String, ArrayList<String>> travelTable = (Map<String, ArrayList<String>>) speedData.get("Traveltable");
                    String description = (String) keydata.get("description");
                    String subdescript = modifier.get("description");
                    TextView textViewdescriptheader = new TextView(RuleLayout.getContext());
                    textViewdescriptheader.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    // rule_header = rule_header.toUpperCase();
                    String descriptionheader = "Special Types of Movement";
                    textViewdescriptheader.setText(descriptionheader);
                    textViewdescriptheader.setTypeface(Typeface.DEFAULT_BOLD);
                    textViewdescriptheader.setTextSize(20);
                    textViewdescriptheader.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                    RuleLayout.addView(textViewdescriptheader);
                    // Add textview 2
                    TextView textViewdescriptbody = new TextView(RuleLayout.getContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                    textViewdescriptbody.setLayoutParams(layoutParams);
                    textViewdescriptbody.setText(description);
                    RuleLayout.addView(textViewdescriptbody);
                    String subheader = "Jumping";
                    TextView subHeader = new TextView(RuleLayout.getContext());
                    TextView subDesc = new TextView(RuleLayout.getContext());
                    subHeader.setLayoutParams(layoutParams);
                    subHeader.setTypeface(Typeface.DEFAULT_BOLD);
                    subHeader.setTextSize(16);
                  //  subHeader.setPadding(15,20,20,20);
                    subDesc.setLayoutParams(layoutParams);
                 //   subDesc.setPadding(15,20,20,20);
                    subHeader.setText(subheader);
                    subDesc.setText(subdescript);
                    RuleLayout.addView(subHeader);
                    RuleLayout.addView(subDesc);
                    keydata.remove("description");
                    modifier.remove("description");
                    keydata.remove("Jumping");
                    speedData.remove("Traveltable");
                    for(Map.Entry<String, String> modifierdata : modifier.entrySet()) {
                        TextView dataHeader = new TextView(RuleLayout.getContext());
                        TextView dataBody = new TextView(RuleLayout.getContext());
                        dataHeader.setTypeface(Typeface.DEFAULT_BOLD);
                        dataHeader.setTextSize(16);
                        dataHeader.setLayoutParams(layoutParams);
                        dataBody.setLayoutParams(layoutParams);
                        dataHeader.setText(modifierdata.getKey());
                        dataBody.setText(modifierdata.getValue());
                        RuleLayout.addView(dataHeader);
                        RuleLayout.addView(dataBody);
                    }
                    for(Map.Entry<String, Object> modifierdata : speedData.entrySet()) {
                        TextView dataHeader = new TextView(RuleLayout.getContext());
                        TextView dataBody = new TextView(RuleLayout.getContext());
                        dataHeader.setTypeface(Typeface.DEFAULT_BOLD);
                        dataHeader.setTextSize(16);
                        dataHeader.setLayoutParams(layoutParams);
                        dataBody.setLayoutParams(layoutParams);
                        dataHeader.setText(modifierdata.getKey());
                        dataBody.setText(modifierdata.getValue().toString());
                        RuleLayout.addView(dataHeader);
                        RuleLayout.addView(dataBody);
                    }
                    TableRow tableHeader = new TableRow(Table1.getContext());
                    TableRow tableBody = new TableRow(Table1.getContext());
                    tableHeader.setLayoutParams(TableRowParams);
                    tableBody.setLayoutParams(TableRowParams);
                    for(Map.Entry<String, ArrayList<String>> tablevalue : travelTable.entrySet()) {
                        TextView spellHeader = new TextView(Table1.getContext());//table header (i.e. Cost)
                        TextView spellDescription = new TextView(Table1.getContext());//table description (i.e. 2$)
                        spellHeader.setLayoutParams(TableParams);
                        spellDescription.setLayoutParams(TableParams);
                        String tableinput = "";
                        ArrayList<String> info = tablevalue.getValue();
                        for (String j : info) {
                            tableinput += j + "\n";
                        }
                        spellHeader.setTypeface(Typeface.DEFAULT_BOLD);
                        spellHeader.setText(tablevalue.getKey());
                        tableHeader.addView(spellHeader);
                        spellDescription.setText(tableinput);
                        tableBody.addView(spellDescription);
                    }
                    Table1.addView(tableHeader);
                    Table1.addView(tableBody);
                    for(Map.Entry<String, Object> data : keydata.entrySet()) {
                        TextView textView1 = new TextView(RuleLayout.getContext());
                        textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                        // rule_header = rule_header.toUpperCase();
                        textView1.setText(data.getKey());
                        textView1.setTypeface(Typeface.DEFAULT_BOLD);
                        textView1.setTextSize(16);
                        textView1.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                        RuleLayout.addView(textView1);
                        // Add textview 2
                        TextView textView2 = new TextView(RuleLayout.getContext());
                        layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                        textView2.setLayoutParams(layoutParams);
                        textView2.setText(data.getValue().toString());
                        RuleLayout.addView(textView2);
                    }
                    ruledata.remove("Special Types of Movement");
                    ruledata.remove("Speed");
                }
                for(Map<String, ArrayList<String>> rule_tables : tables) {
                    Map<String, ArrayList<String>> actualtable = rule_tables;
                    TableRow tableHeader = new TableRow(Table1.getContext());
                    TableRow tableBody = new TableRow(Table1.getContext());
                    tableHeader.setLayoutParams(TableRowParams);
                    tableBody.setLayoutParams(TableRowParams);
                    for(Map.Entry<String, ArrayList<String>> tablevalue : actualtable.entrySet()) {
                        TextView spellHeader = new TextView(Table1.getContext());//table header (i.e. Cost)
                        TextView spellDescription = new TextView(Table1.getContext());//table description (i.e. 2$)
                        spellHeader.setLayoutParams(TableParams);
                        spellDescription.setLayoutParams(TableParams);
                        String tableinput = "";
                        ArrayList<String> info = tablevalue.getValue();
                        for (String j : info) {
                            tableinput += j + "\n";
                        }
                        spellHeader.setTypeface(Typeface.DEFAULT_BOLD);
                        spellHeader.setText(tablevalue.getKey());
                        tableHeader.addView(spellHeader);
                        spellDescription.setText(tableinput);
                        tableBody.addView(spellDescription);
                    }
                    Table1.addView(tableHeader);
                    Table1.addView(tableBody);
                }
                for(Map.Entry<String, Object> rule_info : ruledata.entrySet()) {
                    rule_header = rule_info.getKey();
                    rule_description = rule_info.getValue();
                        TextView textView1 = new TextView(RuleLayout.getContext());
                        textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                       // rule_header = rule_header.toUpperCase();
                        textView1.setText(rule_header);
                        textView1.setTypeface(Typeface.DEFAULT_BOLD);
                        textView1.setTextSize(16);
                        textView1.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                        RuleLayout.addView(textView1);
                        // Add textview 2
                        TextView textView2 = new TextView(RuleLayout.getContext());
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                        textView2.setLayoutParams(layoutParams);
                        textView2.setText(rule_description.toString().replaceAll("\\[|\\]|\\*\\*", ""));
                        RuleLayout.addView(textView2);
                }
            }
        });
    }
}

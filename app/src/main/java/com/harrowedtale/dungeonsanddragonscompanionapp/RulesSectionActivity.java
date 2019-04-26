package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class RulesSectionActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference RulePage;
    private RuleAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feats_layout);

        Intent rulesIntent = getIntent();
        String page = rulesIntent.getStringExtra("Page");
        setTitle(page);
        switch(page){
            case "Ability Scores":
                setUpRecyclerView("AbilityScores");
                break;
            case "Backgrounds":
                setUpRecyclerView("Backgrounds");
                break;
            case "Character Advancement":
                setUpRecyclerView("Character Advancement");
                break;
            case "Combat":
                setUpRecyclerView("Combat");
                break;
            case "Conditions":
                setUpRecyclerView("Conditions");
                break;
            case "Damage Types":
                setUpRecyclerView("DamageTypes");
                break;
            case "Expenses":
                setUpRecyclerView("Expenses");
                break;
            case "Skills":
                setUpRecyclerView("Skills");
                break;
            case "Languages":
                setUpRecyclerView("Languages");
                break;
            case "Movement":
                setUpRecyclerView("Movement");
                break;
            case "Objects":
                setUpRecyclerView("Objects");
                break;
            case "Resting":
                setUpRecyclerView("Resting");
                break;
            case "Mounts and Vehicles":
                setUpRecyclerView("Mounts and Vehicles");
                break;
            case "Time":
                setUpRecyclerView("Time");
                break;
            default:
                setUpRecyclerView("AbilityScores");
        }

    }

    private void setUpRecyclerView(String page) {
        RulePage = db.collection(page);
        Query query = RulePage;
        FirestoreRecyclerOptions<RuleObject> options = new FirestoreRecyclerOptions.Builder<RuleObject>().setQuery(query, RuleObject.class).build();
        adapter = new RuleAdapter(options);

        RecyclerView recyclerView_monster = findViewById(R.id.feats_recycler);//reused from feats_layout
        recyclerView_monster.setHasFixedSize(true);
        recyclerView_monster.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_monster.setAdapter(adapter);

        adapter.setOnItemClickListener(new RuleAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v, String rule_section) {
                Intent Rule_display = new Intent(RulesSectionActivity.this, FullRuleActivity.class);
                Rule_display.putExtra("Name", rule_section);
                Rule_display.putExtra("SectionName", RulePage.getId());
                startActivity(Rule_display);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}

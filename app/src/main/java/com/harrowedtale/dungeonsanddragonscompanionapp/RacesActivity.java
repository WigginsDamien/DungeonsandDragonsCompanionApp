package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class RacesActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference Races = db.collection("Races");
    private RaceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.racepage_layout);
        setUpRecyclerView();
        setTitle("Races");
    }

    private void setUpRecyclerView() {
        Query query = Races;

        FirestoreRecyclerOptions<RaceObject> options = new FirestoreRecyclerOptions.Builder<RaceObject>().setQuery(query, RaceObject.class).build();

        adapter = new RaceAdapter(options);

        RecyclerView recyclerView_monster = findViewById(R.id.race_recycler);
        recyclerView_monster.setHasFixedSize(true);
        recyclerView_monster.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_monster.setAdapter(adapter);

        adapter.setOnItemClickListener(new RaceAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v, String race_name) {
                Intent Race_display = new Intent(RacesActivity.this, FullRaceActivity.class);
                Race_display.putExtra("Name", race_name);
                startActivity(Race_display);
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
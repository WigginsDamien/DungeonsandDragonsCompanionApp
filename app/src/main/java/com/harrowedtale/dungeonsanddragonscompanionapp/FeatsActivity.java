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

public class FeatsActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference Feats = db.collection("Feats");
    private FeatsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feats_layout);
        setUpRecyclerView();
        setTitle("Feats");
    }

    private void setUpRecyclerView() {
        Query query = Feats;

        FirestoreRecyclerOptions<FeatsObject> options = new FirestoreRecyclerOptions.Builder<FeatsObject>().setQuery(query, FeatsObject.class).build();

        adapter = new FeatsAdapter(options);

        RecyclerView recyclerView_monster = findViewById(R.id.feats_recycler);
        recyclerView_monster.setHasFixedSize(true);
        recyclerView_monster.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_monster.setAdapter(adapter);

        adapter.setOnItemClickListener(new FeatsAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent spell_display = new Intent(FeatsActivity.this, FullPlayerClassActivity.class);
                spell_display.putExtra("Name", Feats.getId());
                startActivity(spell_display);
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

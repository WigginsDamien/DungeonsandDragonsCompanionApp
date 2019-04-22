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

public class PlayerClassActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference PlayerClass = db.collection("Classes");
    private PlayerClassAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playerclass_layout);
        setTitle("Classes");
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        Query query = PlayerClass;

        FirestoreRecyclerOptions<PlayerClassObject> options = new FirestoreRecyclerOptions.Builder<PlayerClassObject>().setQuery(query, PlayerClassObject.class).build();

        adapter = new PlayerClassAdapter(options);

        RecyclerView recyclerView_monster = findViewById(R.id.playerclass_recycler);
        recyclerView_monster.setHasFixedSize(true);
        recyclerView_monster.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_monster.setAdapter(adapter);

        adapter.setOnItemClickListener(new PlayerClassAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent spell_display = new Intent(PlayerClassActivity.this, FullPlayerClassActivity.class);
                spell_display.putExtra("Name", PlayerClass.getId());
               // startActivity(spell_display);
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

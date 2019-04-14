package com.harrowedtale.dungeonsanddragonscompanionapp;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpellActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference Spells = db.collection("Spells");
    private SpellAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spellpage_layout);
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        Query query = Spells;

        FirestoreRecyclerOptions<SpellObject> options = new FirestoreRecyclerOptions.Builder<SpellObject>().setQuery(query, SpellObject.class).build();

        adapter = new SpellAdapter(options);

        RecyclerView recyclerView_monster = findViewById(R.id.spell_recycler);
        recyclerView_monster.setHasFixedSize(true);
        recyclerView_monster.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_monster.setAdapter(adapter);

        adapter.setOnItemClickListener(new SpellAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent spell_display = new Intent(SpellActivity.this, FullSpellActivity.class);
                spell_display.putExtra("Name", Spells.getId());
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
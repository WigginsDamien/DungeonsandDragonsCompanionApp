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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SpellActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference Spells = db.collection("Spells");
    private SpellAdapter adapter;
    private Spinner spellSortOption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Spellbook");
        setContentView(R.layout.spellpage_layout);//assign layout
        spellSortOption = findViewById(R.id.spellSortSpinner);//assign spinner from layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spellsort_spinner, android.R.layout.simple_spinner_item);//create adapter
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//assign viewresource
        spellSortOption.setAdapter(adapter);//set adapter to Spinner

        spellSortOption.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String option = (String) parent.getItemAtPosition(position);
                setUpRecyclerView(option);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        setUpRecyclerView("");
    }

    private void setUpRecyclerView(String filter) {
        if(filter.equals("Name")) {
            Query query = Spells.orderBy("name");

            FirestoreRecyclerOptions<SpellObject> options = new FirestoreRecyclerOptions.Builder<SpellObject>().setQuery(query, SpellObject.class).build();

            adapter = new SpellAdapter(options);

            RecyclerView recyclerView_monster = findViewById(R.id.spell_recycler);
            recyclerView_monster.setHasFixedSize(true);
            recyclerView_monster.setLayoutManager(new LinearLayoutManager(this));
            recyclerView_monster.setAdapter(adapter);

            adapter.setOnItemClickListener(new SpellAdapter.ClickListener() {
                @Override
                public void onItemClick(int position, View v, String spell) {
                    Intent spell_display = new Intent(SpellActivity.this, FullSpellActivity.class);
                    spell_display.putExtra("Name", spell);
                    startActivity(spell_display);
                }
            });
            adapter.startListening();
        }
        else if(filter.equals("Level")) {
            Query query = Spells.orderBy("level", Query.Direction.ASCENDING);

            FirestoreRecyclerOptions<SpellObject> options = new FirestoreRecyclerOptions.Builder<SpellObject>().setQuery(query, SpellObject.class).build();

            adapter = new SpellAdapter(options);

            RecyclerView recyclerView_monster = findViewById(R.id.spell_recycler);
            recyclerView_monster.setHasFixedSize(true);
            recyclerView_monster.setLayoutManager(new LinearLayoutManager(this));
            recyclerView_monster.setAdapter(adapter);

            adapter.setOnItemClickListener(new SpellAdapter.ClickListener() {
                @Override
                public void onItemClick(int position, View v, String spell) {
                    Intent spell_display = new Intent(SpellActivity.this, FullSpellActivity.class);
                    spell_display.putExtra("Name", spell);
                    startActivity(spell_display);
                }
            });
            adapter.startListening();
        }
        else {
            Query query = Spells;

            FirestoreRecyclerOptions<SpellObject> options = new FirestoreRecyclerOptions.Builder<SpellObject>().setQuery(query, SpellObject.class).build();

            adapter = new SpellAdapter(options);

            RecyclerView recyclerView_monster = findViewById(R.id.spell_recycler);
            recyclerView_monster.setHasFixedSize(true);
            recyclerView_monster.setLayoutManager(new LinearLayoutManager(this));
            recyclerView_monster.setAdapter(adapter);

            adapter.setOnItemClickListener(new SpellAdapter.ClickListener() {
                @Override
                public void onItemClick(int position, View v, String spell) {
                    Intent spell_display = new Intent(SpellActivity.this, FullSpellActivity.class);
                    spell_display.putExtra("Name", spell);
                    startActivity(spell_display);
                }
            });
            adapter.startListening();
        }
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
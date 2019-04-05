package com.harrowedtale.dungeonsanddragonscompanionapp;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.errorprone.annotations.Var;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MonsterActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference Monsters = db.collection("Monsters");
    private MonsterAdapter adapter;
    private Spinner monstersort_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monsterpage_layout);
        Spinner monstersort_spinner = (Spinner) findViewById(R.id.monstersort_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.monsterfilter_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monstersort_spinner.setAdapter(adapter);
        setUpRecyclerView();

        monstersort_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String option = (String) parent.getItemAtPosition(position);
                //Toast.makeText(view.getContext(), option, Toast.LENGTH_SHORT).show();
              //  setUpRecyclerView(option);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void setUpRecyclerView() {
        Query query = Monsters;

        FirestoreRecyclerOptions<MonsterObject> options = new FirestoreRecyclerOptions.Builder<MonsterObject>().setQuery(query, MonsterObject.class).build();

        adapter = new MonsterAdapter(options);

        RecyclerView recyclerView_monster = findViewById(R.id.monster_recycler);
        recyclerView_monster.setHasFixedSize(true);
        recyclerView_monster.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_monster.setAdapter(adapter);

        adapter.setOnItemClickListener(new MonsterAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v, String monster) {
                Intent monster_display = new Intent(MonsterActivity.this, FullMonsterActivity.class);
                monster_display.putExtra("Name", monster);
                startActivity(monster_display);
            }
        });
    }

    private void setUpRecyclerView(String monsterfilter_option) {
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        Query query = Monsters;
        FirestoreRecyclerOptions<MonsterObject> options = new FirestoreRecyclerOptions.Builder<MonsterObject>().setQuery(query, MonsterObject.class).build();

        adapter = new MonsterAdapter(options);

        RecyclerView recyclerView_monster = findViewById(R.id.monster_recycler);
        recyclerView_monster.setHasFixedSize(true);
        recyclerView_monster.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_monster.setAdapter(adapter);

        adapter.setOnItemClickListener(new MonsterAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v, String monster) {
                Intent monster_display = new Intent(MonsterActivity.this, FullMonsterActivity.class);
                monster_display.putExtra("Name", monster);
                startActivity(monster_display);
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

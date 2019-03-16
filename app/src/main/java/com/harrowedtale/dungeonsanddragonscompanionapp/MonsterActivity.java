package com.harrowedtale.dungeonsanddragonscompanionapp;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
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
import android.widget.Toast;

public class MonsterActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference Monsters = db.collection("Monsters");
    private MonsterAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monsterpage_layout);
        setUpRecyclerView();
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
            public void onItemClick(int position, View v) {
            /*    Fragment newfrag = new BestiaryFull();
                FragmentTransaction display_monster = getSupportFragmentManager().beginTransaction();
                display_monster.add(R.id.test, newfrag).commit();*/
                Intent monster_display = new Intent(MonsterActivity.this, FullMonsterActivity.class);
                monster_display.putExtra("Name", Monsters.getId());
                startActivity(monster_display);
                //Toast.makeText(MonsterActivity.this, "bungus", Toast.LENGTH_SHORT).show(); //used for testing
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

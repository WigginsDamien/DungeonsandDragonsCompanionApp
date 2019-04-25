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

public class ItemActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference Items = db.collection("Equipment");
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_layout);
        setUpRecyclerView();
        setTitle("Items");
    }

    private void setUpRecyclerView() {
        Query query = Items;

        FirestoreRecyclerOptions<ItemObject> options = new FirestoreRecyclerOptions.Builder<ItemObject>().setQuery(query, ItemObject.class).build();

        adapter = new ItemAdapter(options);

        RecyclerView recyclerView_monster = findViewById(R.id.item_recycler);
        recyclerView_monster.setHasFixedSize(true);
        recyclerView_monster.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_monster.setAdapter(adapter);

        adapter.setOnItemClickListener(new ItemAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v, String monster) {
                Intent Item_display = new Intent(ItemActivity.this, FullItemActivity.class);
                Item_display.putExtra("Name", monster);
                startActivity(Item_display);
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
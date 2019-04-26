package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ItemActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference Items = db.collection("Equipment");
    private ItemAdapter adapter;
    private EditText mSearchField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_layout);

        mSearchField = (EditText) findViewById(R.id.search_field);

        setUpRecyclerView("");
        setTitle("Items");

        //See if the text was changed
        mSearchField.addTextChangedListener(new TextWatcher() {
            @Override//Don't mess with, it's fine
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override//When the user types something
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().length() == 0)//If there is no characters put in, display the standard page
                {
                    //Set up the recycler view by the default setting, name
                    setUpRecyclerView("");
                }
                else
                {
                    //Convert the character sequence (s) to a string, and pass it, still sorting by name
                    setUpRecyclerView(s.toString());
                }
            }

            @Override//Don't need anything here
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setUpRecyclerView(String lookup) {
        if(lookup.length() >= 1)
        {
            Query query = Items.orderBy("name").startAt(lookup.trim()).endAt(lookup.trim() + "\uf8ff");

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
            adapter.startListening();
        }
        else
        {
            Query query = Items.orderBy("name");

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
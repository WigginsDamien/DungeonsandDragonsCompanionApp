package com.harrowedtale.dungeonsanddragonscompanionapp;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class MonsterActivity extends AppCompatActivity {


    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference Monsters = db.collection("Monsters");
    private MonsterAdapter adapter;
    private Spinner monstersort_spinner;
    private EditText mSearchField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monsterpage_layout);

        mSearchField = (EditText) findViewById(R.id.search_field);

        Spinner monstersort_spinner = findViewById(R.id.monstersort_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.monsterfilter_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monstersort_spinner.setAdapter(adapter);
        setUpRecyclerView("", "");
        monstersort_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String option = (String) parent.getItemAtPosition(position);
                setUpRecyclerView(option, "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Hello?", Toast.LENGTH_SHORT).show();
            }
        });

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
                    setUpRecyclerView("Name", "");
                }
                else
                {
                    //Convert the character sequence (s) to a string, and pass it, still sorting by name
                    setUpRecyclerView("Name", s.toString());
                }
            }

            @Override//Don't need anything here
            public void afterTextChanged(Editable s) {

            }
        });
    }



    private void setUpRecyclerView(String filter, String lookup) {
        if(filter.equals("Name")) {
            if(lookup.length() >= 1)//If the string can be used to lookup something, no just ""
            {
                //Changed the query, so that you search for the term
                Query query = Monsters.orderBy("name").startAt(lookup.trim()).endAt(lookup.trim() + "\uf8ff");
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

                adapter.startListening();
            }
            else//Normal case of just querying without searching
            {
                Query query = Monsters.orderBy("name");
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

                adapter.startListening();
            }
        }
        else if(filter.equals("Challenge Rating")) {

            Query query = Monsters.orderBy("challengeRating");
            FirestoreRecyclerOptions<MonsterObject> options = new FirestoreRecyclerOptions.Builder<MonsterObject>().setQuery(query, MonsterObject.class).build();

            adapter = new MonsterAdapter(options);

            RecyclerView recyclerView_monster = findViewById(R.id.monster_recycler);
            recyclerView_monster.setHasFixedSize(true);
            recyclerView_monster.setLayoutManager(new LinearLayoutManager(this));
            recyclerView_monster.setAdapter(adapter);
            recyclerView_monster.getAdapter().notifyDataSetChanged();

            adapter.setOnItemClickListener(new MonsterAdapter.ClickListener() {
                @Override
                public void onItemClick(int position, View v, String monster) {
                    Intent monster_display = new Intent(MonsterActivity.this, FullMonsterActivity.class);
                    monster_display.putExtra("Name", monster);
                    startActivity(monster_display);

                    adapter.notifyDataSetChanged();
                }
            });
            adapter.startListening();
        }
        else if(filter.equals("Type")) {
            Query query = Monsters.orderBy("type");
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
            adapter.startListening();
        }
        else {
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

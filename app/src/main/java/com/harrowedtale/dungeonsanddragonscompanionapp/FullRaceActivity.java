package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Map;

public class FullRaceActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private TextView race_data;
    public LinearLayout RaceLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.race_fullinformation);
        RaceLayout = findViewById(R.id.raceLinLayout);
        Intent race = getIntent();
        String raceName = race.getStringExtra("Name");
        setTitle(raceName);

        DocumentReference race_ref = db.collection("Races").document(raceName);
        race_ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                String race_header = "";
                String race_description = "";
                DocumentSnapshot race = task.getResult();
                Map<String, String> basic = (Map<String, String>) race.get("Basic");
                for(Map.Entry<String, String> basic_data : basic.entrySet()) {
                    race_header = basic_data.getKey();
                    race_description = basic_data.getValue();
                    TextView textView1 = new TextView(RaceLayout.getContext());
                    textView1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                            LayoutParams.WRAP_CONTENT));
                    textView1.setText(race_header);
                    textView1.setTypeface(Typeface.DEFAULT_BOLD);
                    textView1.setTextSize(16);
                    textView1.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                    RaceLayout.addView(textView1);

                    // Add textview 2
                    TextView textView2 = new TextView(RaceLayout.getContext());
                    LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                            LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                    textView2.setLayoutParams(layoutParams);
                    textView2.setText(race_description);
                    RaceLayout.addView(textView2);

                }
                if(race.get("Subrace") != "") {
//                    race_header += "\n\nSubrace\n";
                    TextView textView0 = new TextView(RaceLayout.getContext());
                    textView0.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                            LayoutParams.WRAP_CONTENT));
                    textView0.setTypeface(Typeface.DEFAULT_BOLD);
                    textView0.setTextSize(24);
                    textView0.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                    Map<String, String> subrace = (Map<String, String>) race.get("Subrace");
                    textView0.setText(subrace.get("Name"));
                    textView0.setTextColor(Color.parseColor("#6d1b1b"));
                    subrace.remove("Name");
                    RaceLayout.addView(textView0);
                    for(Map.Entry<String, String> basic_data : subrace.entrySet()) {
                        if(basic_data.getKey().equals("Name")) {
      //                      race_description = basic_data.getValue();

                        }
                        else
                        {
                            race_description = basic_data.getValue();
                            race_header = basic_data.getKey();
                        }
                        TextView textView1 = new TextView(RaceLayout.getContext());
                        textView1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                                LayoutParams.WRAP_CONTENT));
                        textView1.setText(race_header);
                        textView1.setTypeface(Typeface.DEFAULT_BOLD);
                        textView1.setTextSize(16);
                        textView1.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                        RaceLayout.addView(textView1);

                        // Add textview 2
                        TextView textView2 = new TextView(RaceLayout.getContext());
                        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                                LayoutParams.WRAP_CONTENT);
                        layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                        textView2.setLayoutParams(layoutParams);
                        textView2.setText(race_description);
                        RaceLayout.addView(textView2);

                    }
                }
                if(race.contains("Tinker")) {
  //                  race_info += "Tinker\n";
                    TextView textView0 = new TextView(RaceLayout.getContext());
                    textView0.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                            LayoutParams.WRAP_CONTENT));
                    textView0.setText("Tinker");
                    textView0.setTypeface(Typeface.DEFAULT_BOLD);
                    textView0.setTextSize(16);
                    textView0.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                    RaceLayout.addView(textView0);
                    Map<String, String> tinker = (Map<String, String>) race.get("Tinker");
                    for(Map.Entry<String, String> basic_data : tinker.entrySet()) {
                        if(basic_data.getKey().equals("Name")) {
                            race_description = basic_data.getValue();

                        }
                        else
                        {
                            race_description = basic_data.getValue();
                            race_header = basic_data.getKey();
                        }
                        TextView textView1 = new TextView(RaceLayout.getContext());
                        textView1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                                LayoutParams.WRAP_CONTENT));
                        textView1.setText(race_header);
                        textView1.setTypeface(Typeface.DEFAULT_BOLD);
                        textView1.setTextSize(16);
                        textView1.setPadding(15, 20, 20, 20);// in pixels (left, top, right, bottom)
                        RaceLayout.addView(textView1);

                        // Add textview 2
                        TextView textView2 = new TextView(RaceLayout.getContext());
                        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                                LayoutParams.WRAP_CONTENT);
                        layoutParams.setMargins(15, 10, 10, 10); // (left, top, right, bottom)
                        textView2.setLayoutParams(layoutParams);
                        textView2.setText(race_description);
                        RaceLayout.addView(textView2);
                    }
                }
  //              race_data.setText(race_info);
            }

        });
    }
}


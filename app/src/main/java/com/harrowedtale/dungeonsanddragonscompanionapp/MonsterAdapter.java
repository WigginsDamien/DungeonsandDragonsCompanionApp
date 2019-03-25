package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import io.opencensus.tags.TagContext;

public class MonsterAdapter extends FirestoreRecyclerAdapter<MonsterObject, MonsterAdapter.MonsterHolder> {
    private static ClickListener clicklistener;

    public MonsterAdapter(@NonNull FirestoreRecyclerOptions<MonsterObject> options){
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MonsterHolder monster, int position, @NonNull MonsterObject model) {
        monster.tvName.setText(getSnapshots().getSnapshot(position).getReference().getId());
        String challengeRating = "Challenge Rating: " + String.valueOf(getSnapshots().getSnapshot(position).get("challengeRating"));
        String hitPoints = "Hitpoints: " + String.valueOf(getSnapshots().getSnapshot(position).get("hitPoints"));
        monster.tvCR.setText(challengeRating);
        monster.tvHP.setText(hitPoints);
    }

    @NonNull
    @Override
    public MonsterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_monster, parent, false);
        return new MonsterHolder(v);
    }

    public class MonsterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;
        TextView tvCR;
        TextView tvHP;

        public MonsterHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvName = itemView.findViewById(R.id.monster_name);
            tvCR = itemView.findViewById(R.id.monster_CR);
            tvHP = itemView.findViewById(R.id.monster_HP);
        }

        @Override
        public void onClick(View v) {
            String monster_name = getSnapshots().getSnapshot(getAdapterPosition()).getId();
            clicklistener.onItemClick(getAdapterPosition(), v, monster_name);
        }

    }

    public void setOnItemClickListener(ClickListener clicklistener) {
        MonsterAdapter.clicklistener = clicklistener;
    }
    public interface ClickListener {
        void onItemClick(int position, View v, String monster);
    }
}

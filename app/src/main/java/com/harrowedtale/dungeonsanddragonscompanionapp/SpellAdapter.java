package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class SpellAdapter  extends FirestoreRecyclerAdapter <SpellObject, SpellAdapter.SpellHolder> {
    private static ClickListener clicklistener;

    public SpellAdapter(@NonNull FirestoreRecyclerOptions<SpellObject> options){
        super(options);
    }

    //@Override
    protected void onBindViewHolder(@NonNull SpellHolder spell, int position, @NonNull SpellObject model) {
        spell.tvName.setText(getSnapshots().getSnapshot(position).getReference().getId());
        String level = "Level: " + String.valueOf(getSnapshots().getSnapshot(position).get("level"));
        String castingTime = "Casting Time: " + String.valueOf(getSnapshots().getSnapshot(position).get("castingTime"));
        spell.tvLvl.setText(level);
        spell.tvCT.setText(castingTime);
    }

    @NonNull
    @Override
    public SpellHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_spell, parent, false);
        return new SpellHolder(v);
    }

    public class SpellHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;
        TextView tvLvl;
        TextView tvCT;

        public SpellHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvName = itemView.findViewById(R.id.spell_name);
            tvLvl = itemView.findViewById(R.id.spell_level);
            tvCT = itemView.findViewById(R.id.spell_castingTime);
        }

        @Override
        public void onClick(View v) {
            clicklistener.onItemClick(getAdapterPosition(), v);
        }

    }

    public void setOnItemClickListener(ClickListener clicklistener) {
        SpellAdapter.clicklistener = clicklistener;
    }
    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}
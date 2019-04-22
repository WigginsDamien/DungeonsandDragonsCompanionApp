package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class PlayerClassAdapter extends FirestoreRecyclerAdapter<PlayerClassObject, PlayerClassAdapter.PlayerClassHolder> {
    private static PlayerClassAdapter.ClickListener clicklistener;

    public PlayerClassAdapter(@NonNull FirestoreRecyclerOptions<PlayerClassObject> options){
        super(options);
    }

    //@Override
    protected void onBindViewHolder(@NonNull PlayerClassAdapter.PlayerClassHolder spell, int position, @NonNull PlayerClassObject model) {
        spell.tvName.setText(getSnapshots().getSnapshot(position).getReference().getId());
    }

    @NonNull
    @Override
    public PlayerClassAdapter.PlayerClassHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_playerclass, parent, false);
        return new PlayerClassAdapter.PlayerClassHolder(v);
    }

    public class PlayerClassHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;
        public PlayerClassHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvName = itemView.findViewById(R.id.class_name);
        }

        @Override
        public void onClick(View v) {
           clicklistener.onItemClick(getAdapterPosition(), v);
        }

    }

    public void setOnItemClickListener(PlayerClassAdapter.ClickListener clicklistener) {
        PlayerClassAdapter.clicklistener = clicklistener;
    }
    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}

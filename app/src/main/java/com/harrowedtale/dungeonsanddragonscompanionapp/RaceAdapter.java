package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.Map;

public class RaceAdapter extends FirestoreRecyclerAdapter <RaceObject, RaceAdapter.ItemHolder> {
    private static ClickListener clicklistener;

    public RaceAdapter(@NonNull FirestoreRecyclerOptions<RaceObject> options){
        super(options);
    }

    //@Override
    protected void onBindViewHolder(@NonNull ItemHolder Item, int position, @NonNull RaceObject model) {
        Item.tvName.setText(getSnapshots().getSnapshot(position).getId());
        if(model.getSubrace().containsKey("Name")) {
            Item.tvSubrace.setText(model.getSubrace().get("Name"));
        }
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_race, parent, false);
        return new ItemHolder(v);
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;
        TextView tvSubrace;

        public ItemHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvName = itemView.findViewById(R.id.race_name);
            tvSubrace = itemView.findViewById(R.id.subrace_name);
        }

        @Override
        public void onClick(View v) {
            String race_name = getSnapshots().getSnapshot(getAdapterPosition()).getId();
            clicklistener.onItemClick(getAdapterPosition(), v, race_name);
        }

    }

    public void setOnItemClickListener(ClickListener clicklistener) {
        RaceAdapter.clicklistener = clicklistener;
    }
    public interface ClickListener {
        void onItemClick(int position, View v, String race_name);
    }
}
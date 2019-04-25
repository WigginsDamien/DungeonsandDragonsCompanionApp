package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class FeatsAdapter extends FirestoreRecyclerAdapter<FeatsObject, FeatsAdapter.FeatsHolder> {
    private static FeatsAdapter.ClickListener clicklistener;

    public FeatsAdapter(@NonNull FirestoreRecyclerOptions<FeatsObject> options){
        super(options);
    }

    //@Override
    protected void onBindViewHolder(@NonNull FeatsAdapter.FeatsHolder spell, int position, @NonNull FeatsObject model) {
        spell.tvName.setText(getSnapshots().getSnapshot(position).getReference().getId());
        String prerequisite = "Pre-requisite: " + model.getPrerequisite();
        spell.tvLvl.setText(prerequisite);
        String description = "Description: " + model.getDescription();
        spell.tvDescription.setText(description);
    }

    @NonNull
    @Override
    public FeatsAdapter.FeatsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_feat, parent, false);
        return new FeatsAdapter.FeatsHolder(v);
    }

    public class FeatsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;
        TextView tvLvl;
        TextView tvDescription;
        public FeatsHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvName = itemView.findViewById(R.id.feat_name);
            tvLvl = itemView.findViewById(R.id.feat_prereq);
            tvDescription = itemView.findViewById(R.id.feat_description);
        }

        @Override
        public void onClick(View v) {
            //clicklistener.onItemClick(getAdapterPosition(), v);
        }

    }

    public void setOnItemClickListener(FeatsAdapter.ClickListener clicklistener) {
        FeatsAdapter.clicklistener = clicklistener;
    }
    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}

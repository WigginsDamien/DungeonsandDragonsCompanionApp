package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class RuleAdapter extends FirestoreRecyclerAdapter <RuleObject, RuleAdapter.RuleHolder> {
    private static ClickListener clicklistener;

    public RuleAdapter(@NonNull FirestoreRecyclerOptions<RuleObject> options){
        super(options);
    }

    //@Override
    protected void onBindViewHolder(@NonNull RuleHolder Rule, int position, @NonNull RuleObject model) {
        Rule.tvName.setText(getSnapshots().getSnapshot(position).getReference().getId());
    }

    @NonNull
    @Override
    public RuleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_playerclass, parent, false);
        return new RuleHolder(v);
    }

    public class RuleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;

        public RuleHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvName = itemView.findViewById(R.id.class_name);//reused from recycler_view_playerclass
        }

        @Override
        public void onClick(View v) {
            String rule = getSnapshots().getSnapshot(getAdapterPosition()).getId();
            clicklistener.onItemClick(getAdapterPosition(), v, rule);
        }

    }

    public void setOnItemClickListener(ClickListener clicklistener) {
        RuleAdapter.clicklistener = clicklistener;
    }
    public interface ClickListener {
        void onItemClick(int position, View v, String rule);
    }
}
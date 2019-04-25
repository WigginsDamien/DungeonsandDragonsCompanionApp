package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ItemAdapter extends FirestoreRecyclerAdapter <ItemObject, ItemAdapter.ItemHolder> {
    private static ClickListener clicklistener;

    public ItemAdapter(@NonNull FirestoreRecyclerOptions<ItemObject> options){
        super(options);
    }

    //@Override
    protected void onBindViewHolder(@NonNull ItemHolder Item, int position, @NonNull ItemObject model) {
        Item.tvName.setText(model.getName());
        String itemCost = "Cost: " + model.getCost();
        String itemWeight = "Weight: " + String.valueOf(model.getWeight());
        String itemCategory = "Category: " + model.getEquipmentCategory();
        Item.tvCost.setText(itemCost);
        Item.tvWeight.setText(itemWeight);
        Item.tvCategory.setText(itemCategory);
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new ItemHolder(v);
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;
        TextView tvWeight;
        TextView tvCost;
        TextView tvCategory;

        public ItemHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvName = itemView.findViewById(R.id.item_name);
            tvWeight = itemView.findViewById(R.id.item_weight);
            tvCost = itemView.findViewById(R.id.item_cost);
            tvCategory = itemView.findViewById(R.id.item_category);
        }

        @Override
        public void onClick(View v) {
            String monster_name = getSnapshots().getSnapshot(getAdapterPosition()).getId();
            clicklistener.onItemClick(getAdapterPosition(), v, monster_name);
        }

    }

    public void setOnItemClickListener(ClickListener clicklistener) {
        ItemAdapter.clicklistener = clicklistener;
    }
    public interface ClickListener {
        void onItemClick(int position, View v, String monster_name);
    }
}
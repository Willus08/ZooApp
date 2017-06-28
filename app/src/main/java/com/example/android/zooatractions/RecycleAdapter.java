package com.example.android.zooatractions;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 6/27/2017.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder>{

    List<Animals> animalsList = new ArrayList<>();
    public RecycleAdapter(List<Animals> animalsList) {
        this.animalsList = animalsList;
    }


    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecycleAdapter.ViewHolder holder, int position) {
        Animals animals = animalsList.get(position);
        holder.tvNumber.setText(animals.getNumber());
        holder.tvSpecies.setText(animals.getSpecies());

    }

    @Override
    public int getItemCount() {
        return animalsList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSpecies;
        TextView tvNumber;
        public ViewHolder(View itemView) {
            super(itemView);
            tvSpecies = (TextView) itemView.findViewById(R.id.tvAnimalName);
            tvNumber = (TextView) itemView.findViewById(R.id.tvAnimalAmount);
        }
    }
}

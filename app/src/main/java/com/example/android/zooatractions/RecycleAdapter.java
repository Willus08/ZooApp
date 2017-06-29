package com.example.android.zooatractions;

import android.content.Intent;
import android.database.Observable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

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
        private static final String TAG ="testing" ;
        TextView tvSpecies;
        TextView tvNumber;
        public ViewHolder(View itemView) {
            super(itemView);
            tvSpecies = (TextView) itemView.findViewById(R.id.tvAnimalName);
            tvNumber = (TextView) itemView.findViewById(R.id.tvAnimalAmount);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: " + tvSpecies.getText());
                    EventBus.getDefault().post(new PassingEvent(tvSpecies.getText().toString()));
                }
            });
        }
    }
}

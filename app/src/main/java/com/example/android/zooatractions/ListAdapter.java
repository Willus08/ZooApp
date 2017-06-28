package com.example.android.zooatractions;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Android on 6/27/2017.
 */

public class ListAdapter extends ArrayAdapter<String> {

    TextView tvGroup;


    Context context;

    public ListAdapter(Context context, int resourceId, List<String> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /*private view holder class*/
    private class ViewHolder {
        TextView tvGroup;
        TextView tvNumber;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        //Animals animals= getItem(position);
        String groups = getItem(position);
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_items, null);
            holder = new ViewHolder();
            holder.tvGroup = (TextView) convertView.findViewById(R.id.tvAnimalName);
            holder.tvNumber = (TextView) convertView.findViewById(R.id.tvAnimalAmount);

            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.tvGroup.setText(groups);
       // holder.tvNumber.setText(animals.getNumber());


        return convertView;
    }


}

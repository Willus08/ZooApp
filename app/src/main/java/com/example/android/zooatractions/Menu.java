package com.example.android.zooatractions;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Menu extends AppCompatActivity implements  AdapterView.OnItemClickListener {

    private static final String TAG = "testing";
    private ListView listView;
    private ListAdapter listAdapter;
    private TextView text;
    private LinearLayout listHolder;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private DefaultItemAnimator defaultItemAnimator;
    private List<Animals> animalsList = new ArrayList<>();
    private List<String> groupsList = new ArrayList<>();
    private List<Animals> groupedAnimals = new ArrayList<>();
    private RecycleAdapter recycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        PoulateList();
        Populatedatabase();
        fillGroupList();

        listView = (ListView) findViewById(R.id.lvMenu1);
        recyclerView = (RecyclerView) findViewById(R.id.rlMenu2);
        text =(TextView) findViewById(R.id.tvAnimalName);
        listHolder = (LinearLayout) findViewById(R.id.llListContainer);

        listAdapter = new ListAdapter(this, R.layout.list_items, groupsList);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(this);

        layoutManager = new LinearLayoutManager(this);
        defaultItemAnimator = new DefaultItemAnimator();
        recycleAdapter = new RecycleAdapter(groupedAnimals);


        recyclerView.setVisibility(View.GONE);
         recyclerView.setLayoutManager(layoutManager);
         recyclerView.setItemAnimator(defaultItemAnimator);
         recyclerView.setAdapter(recycleAdapter);



     }

    private void fillGroupList() {
        SQLSetup data = new SQLSetup(this);
        String[] g = data.getUniqueGroups();
        for (int i = 0; i <g.length ; i++) {
            Log.d(TAG, "fillGroup list: animals " + i);
            groupsList.add(g[i]);

        }

    }

    private void fillGroupedAnimals(String g) {
        SQLSetup data = new SQLSetup(this);
        Animals[] a = data.getByGroup(g);
        for (int i = 0; i < a.length ; i++) {
            Log.d(TAG, "fillGroupedAnimals: animals " + i);
            groupedAnimals.add( a[i]);

        }
    }

    public void PoulateList(){
         animalsList.add(new Animals("Species","random","thing","here","is","animals"));
        animalsList.add(new Animals("Species4","random","thing","here","is","animals"));
        animalsList.add(new Animals("Species5","random","thing","here","is","animals"));
        animalsList.add(new Animals("Species8","random","thing","here","is","animals"));
        animalsList.add(new Animals("Species10","random","thing","here","is","animals"));
         animalsList.add(new Animals("Species1","random","thing","here","is","mythical"));
         animalsList.add(new Animals("Species2","random","thing","here","is","doomed"));
         animalsList.add(new Animals("Species3","random","thing","here","is","mamals"));
     }

     public void Populatedatabase(){
         SQLSetup data = new SQLSetup(this);
         if (data.getDatabaseName()!= null){
             for (int i = 0; i < animalsList.size(); i++) {
                data.SaveAnimal(animalsList.get(i));
             }

         }
     }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        listView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        Log.d(TAG, "onItemClick: postion clicked" + position);
        fillGroupedAnimals(groupsList.get(position).toString());



    }

    


}

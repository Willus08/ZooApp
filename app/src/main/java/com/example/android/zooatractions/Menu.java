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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class Menu extends AppCompatActivity implements  AdapterView.OnItemClickListener {

    private static final String TAG = "testing";
    private ListView listView;
    private ListAdapter listAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private DefaultItemAnimator defaultItemAnimator;
    private List<Animals> animalsList = new ArrayList<>();
    private List<String> groupsList = new ArrayList<>();
    private List<Animals> groupedAnimals = new ArrayList<>();
    private RecycleAdapter recycleAdapter;
    private String Animal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        PoulateList();
        Populatedatabase();
        fillGroupList();

        listView = (ListView) findViewById(R.id.lvMenu1);
        recyclerView = (RecyclerView) findViewById(R.id.rlMenu2);

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
        groupedAnimals.clear();
        Animals[] a = data.getByGroup(g);
        for (int i = 0; i < a.length ; i++) {
            Log.d(TAG, "fillGroupedAnimals: animals " + i);
            groupedAnimals.add( a[i]);

        }
    }

    public void PoulateList(){
                                    //species, description,sound,image,number,group
        animalsList.add(new Animals("Dragon","Aincient dragon of yore",R.raw.dragon,R.raw.dragon_pic,"2","Mythical"));
//        animalsList.add(new Animals("Phoneix","Bird that reseructs from the dead","thing","here","3","Mythical"));
//        animalsList.add(new Animals("Kraken","Destroyer of ships","thing","here","3","Mythical"));
//        animalsList.add(new Animals("Grifon","Winged death","thing","here","25","Mythical"));
//        animalsList.add(new Animals("Hydra","multi headed beast","thing","here","3","Mythical"));
//
//        animalsList.add(new Animals("Penguin","Cool tuxedo conisur","thing","here","300","Birds"));
//        animalsList.add(new Animals("Pelican","Aquatic thief","thing","here","15","Birds"));
//        animalsList.add(new Animals("Pecock","NBC Mascot","thing","here","13","Birds"));
//        animalsList.add(new Animals("Parrot","Natures recorder","thing","here","is","Birds"));
//        animalsList.add(new Animals("Perguin Falcon","A cool Falcon","thing","here","is","Birds"));
//
//        animalsList.add(new Animals("Shark","Devour of meats","thing","here","35","Sea"));
//        animalsList.add(new Animals("Dolphin","Shark Slayer","thing","here","125","Sea"));
//        animalsList.add(new Animals("Whale","Devourer of kelp","thing","here","5","Sea"));
//        animalsList.add(new Animals("Whale Shark","Devourer of everything","thing","here","10","Sea"));
//        animalsList.add(new Animals("Platapus","Epic Secret Agent","thing","here","20","Sea"));
//
//        animalsList.add(new Animals("Ninja","worlds higest populs yet always unseen","thing","here","10,000","Mammals"));
//        animalsList.add(new Animals("Pirate","Plundere of treasure","thing","here","300","Mammals"));
//        animalsList.add(new Animals("Liger","The coolest animal ever","thing","here","10","Mammals"));
//        animalsList.add(new Animals("Panda","Best thing ever","thing","here","20","Mammals"));
//        animalsList.add(new Animals("Programer","the writers of this app","thing","here","2","Mammals"));
//
//        animalsList.add(new Animals("Ant","common everwhere but here","thing","here","1","Misc"));
//        animalsList.add(new Animals("Scorpion","Taker of souls","thing","here","1","Misc"));
//        animalsList.add(new Animals("Snorlax","Blocker of paths","thing","here","20","Misc"));
//        animalsList.add(new Animals("Snuffalapugus","more cuddly than an elephent","thing","here","1","Misc"));
//        animalsList.add(new Animals("Demon","Real taker of souls","thing","here","2","Misc"));
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

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messaged(PassingEvent event){
        listView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        SQLSetup data = new SQLSetup(this);
        Animal = event.getPassed();
        Intent intent = new Intent(Menu.this,AnimalPicture.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("animal",new Animals(data.getBySpecies(Animal)));
        intent.putExtras(bundle);
        startActivity(intent);

    }

}

package com.example.android.zooatractions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class AnimalPicture extends AppCompatActivity implements Serializable {
    private Animals shown;
    private TextView textViewName;
    private TextView textViewdescription;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_picture);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        shown = (Animals) bundle.get("animal");
        textViewName = (TextView) findViewById(R.id.tvName);
        textViewdescription = (TextView) findViewById(R.id.tvDescription);
        imageView = (ImageView) findViewById(R.id.ivPicture);

        textViewName.setText(shown.getSpecies());
        textViewdescription.setText(shown.getDescription());
    }

    public void playSound(View view) {
    }
}

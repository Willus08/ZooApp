package com.example.android.zooatractions;

import android.content.Intent;
import android.drm.DrmStore;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;

public class AnimalPicture extends AppCompatActivity implements Serializable {
    private static final String TAG = "test";
    private Animals shown;
    private TextView textViewName;
    private TextView textViewdescription;
    private ImageView imageView;
    private InputStream in;

    Uri file;
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
        //imageView.setImageURI(Uri.parse("C:\\Users\\Android\\AndroidStudioProjects\\ZooAtractions\\app\\src\\main\\assets\\Pictures\\dragon_pic.jpg.jpg"));

        Log.d(TAG, "onCreate: "+shown.getImageLocation().toString());
        Log.d(TAG, "onCreate: "+shown.getSoundLocation().toString());
        File file = getFileStreamPath(shown.getImageLocation());
        imageView.setImageURI(Uri.parse(shown.getImageLocation()));
        imageView.setImageURI(Uri.parse("dragon_pic.jpg"));
        imageView.setImageURI(Uri.parse("C:\\Users\\Android\\AndroidStudioProjects\\ZooAtractions\\app\\src\\main\\res\\raw\\dragon_pic.jpg"));
        imageView.setImageURI(Uri.parse("C:\\Users\\Android\\AndroidStudioProjects\\ZooAtractions\\app\\src\\main\\res\\raw\\dragon_pic.jpg"));
    }

    public void playSound(View view) throws IOException, URISyntaxException {
        String temp = shown.getSoundLocation();
        File file = getFileStreamPath(shown.getSoundLocation());
        Log.d(TAG, "playSound: " + file);
        MediaPlayer mediaPlayer = MediaPlayer.create(this, Uri.parse("C:\\Users\\Android\\AndroidStudioProjects\\ZooAtractions\\app\\src\\main\\assets\\Sounds\\dragon_pic.mp3"));


        mediaPlayer.start();





    }
}

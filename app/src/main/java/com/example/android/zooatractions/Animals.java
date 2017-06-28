package com.example.android.zooatractions;

import java.io.Serializable;

/**
 * Created by Android on 6/27/2017.
 */

public class Animals implements Serializable {



    String Species;
    String Description;
    String SoundLocation;
    String ImageLocation;
    String Number;
    String Group;//pk

    public Animals(String species, String description, String soundLocation, String imageLocation, String number, String group) {
        Species = species;
        Description = description;
        SoundLocation = soundLocation;
        ImageLocation = imageLocation;
        Number = number;
        Group = group;
    }

    public Animals(Animals animals) {
        Species = animals.getSpecies();
        Description = animals.getDescription();
        SoundLocation = animals.getSoundLocation();
        ImageLocation = animals.getImageLocation();
        Number = animals.getNumber();
        Group = animals.getGroup();
    }

    public String getDescription() {
        return Description;
    }

    public String getSpecies() {
        return Species;
    }

    public String getSoundLocation() {
        return SoundLocation;
    }

    public String getImageLocation() {
        return ImageLocation;
    }

    public String getNumber() {
        return Number;
    }

    public String getGroup() {
        return Group;
    }
}

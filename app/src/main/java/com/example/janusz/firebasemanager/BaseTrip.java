package com.example.janusz.firebasemanager;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tomasz on 28.05.2017.
 */

public class BaseTrip {

    private long estimatedTime;
    private float rate;
    private String photoURL;
    private String name;
    private ArrayList<Atrakcja> attractions;

    public BaseTrip()
    {
        setName("");
        setAttractions(new ArrayList<Atrakcja>());
        setRate(0);
    }

    public BaseTrip(String name, String photoURL)
    {
        this.setPhotoURL(photoURL);
        this.setName(name);
        setAttractions(new ArrayList<Atrakcja>());
        setRate(0);
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("estimatedTime", estimatedTime);
        result.put("rate", rate);
        result.put("photoURL", photoURL);
        result.put("name", name);
        final HashMap<String, String> attmap = new HashMap<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        for (Atrakcja atrakcja : attractions) {
            String key;
            switch (atrakcja.getNazwa()) {
                case "Wrocławskie ZOO":
                    key = "-KljjVObiZB89VNrtuwZ";
                    break;
                case "Rynek":
                    key = "-KljjVPUILzuiIt_7n5H";
                    break;
                case "Hala Stulecia":
                    key = "-KljjVPQZpME87hJIwAR";
                    break;
                default:
                    key = "[";
            }
            attmap.put(key, atrakcja.getNazwa());
        }
        result.put("attractions", attmap);

        return result;
    }


    public void addAttraction(Atrakcja attraction)
    {
        getAttractions().add(attraction);
    }

    public long getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(long estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Atrakcja> getAttractions() {
        return attractions;
    }

    public void setAttractions(ArrayList<Atrakcja> attractions) {
        this.attractions = attractions;
    }
}

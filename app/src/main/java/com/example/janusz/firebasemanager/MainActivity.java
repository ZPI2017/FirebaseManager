package com.example.janusz.firebasemanager;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*
    AKTUALNA KONFIGURACJA BAZY:

    categories : {
        [cat1] : {
            id : "[cat1]"
            name: "[nazwa]"
        }
        [cat2] : {
            ...
        }
    }

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences shared = getSharedPreferences("com.example.janusz.firebasemanager", MODE_PRIVATE);
        if (shared.getBoolean("firstrun", true)) {

            //tu kod do wykonania operacji na bazie


            Toast.makeText(MainActivity.this, "Pojszło", Toast.LENGTH_LONG).show();
            shared.edit().putBoolean("firstrun",false).commit();
        }
    }

    /*private void modifyCategories() {
        final ArrayList<Category> cats = new ArrayList<>();

        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("categories");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot category : dataSnapshot.getChildren()) {
                    cats.add(new Category(category.getKey().toString(), category.getValue().toString()));
                }
                for (Category c : cats) {
                    mDatabase.child(c.id).setValue(c);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Cos sie zjebalo", Toast.LENGTH_LONG).show();
            }
        });
    }*/
}

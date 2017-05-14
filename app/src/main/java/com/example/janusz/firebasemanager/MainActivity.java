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

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences shared = getSharedPreferences("com.example.janusz.firebasemanager", MODE_PRIVATE);
        if (shared.getBoolean("firstrun", true)) {
            mDatabase = FirebaseDatabase.getInstance().getReference();
            //tu kod do wykonania operacji na bazie


            Toast.makeText(MainActivity.this, "Pojszło", Toast.LENGTH_LONG).show();
            shared.edit().putBoolean("firstrun",false).commit();
        }
    }

    /*private void modifyCategories() {
        final ArrayList<Category> cats = new ArrayList<>();

        DatabaseReference mCategories = mDatabase.child("categories");
        mCategories.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot category : dataSnapshot.getChildren()) {
                    cats.add(new Category(category.getKey().toString(), category.getValue().toString()));
                }
                for (Category c : cats) {
                    mCategories.child(c.id).setValue(c);
                }
            Toast.makeText(MainActivity.this, "Tu Też Pojszło", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Cos sie zjebalo", Toast.LENGTH_LONG).show();
            }
        });
    }*/
}

package com.example.janusz.firebasemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /*
    AKTUALNA KONFIGURACJA BAZY:

    places : {
        [place1] : {
            latitude : double
            longitude : double
            nazwa : String
            zsczegoly : String
            zdjecieURL : String
        }
    }

    locations : {
        [place1] : {
            latitude : double
            longitude : double
        }
    }

    categories : {
        [cat1] : {
            id : "[cat1]"
            name: "[nazwa]"
        }
    }

     */

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void click(View view) {
        addPrzykladoweWycieczki2();
    }

    private void modifyLocations() {
        mDatabase.child("places").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot att : dataSnapshot.getChildren()) {
                    DatabaseReference mLokacje = mDatabase.child("locations");
                        String key = att.getKey();
                        mLokacje.child(key).child("lattitude").setValue(att.child("lattitude").getValue());
                        mLokacje.child(key).child("longitude").setValue(att.child("longitude").getValue());
                        mLokacje.child(key).child("name").setValue(att.child("name").getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addPrzykladoweWycieczki2() {
        BaseTrip[] trips;

        trips = new BaseTrip[3];
        trips[0] = new BaseTrip("Najlepsze atrakce", "https://firebasestorage.googleapis.com/v0/b/zpi2017-77741.appspot.com/o/hala.jpeg?alt=media&token=a3ed343e-8f79-434a-9b67-2b72a20366a7");
        trips[0].setRate(4);
        trips[1] = new BaseTrip("Wycieczka2", "https://firebasestorage.googleapis.com/v0/b/zpi2017-77741.appspot.com/o/hala.jpeg?alt=media&token=a3ed343e-8f79-434a-9b67-2b72a20366a7");
        trips[2] = new BaseTrip("Wycieczka3", "https://firebasestorage.googleapis.com/v0/b/zpi2017-77741.appspot.com/o/hala.jpeg?alt=media&token=a3ed343e-8f79-434a-9b67-2b72a20366a7");

        trips[0].addAttraction(new Atrakcja("Hala Stulecia", "Costam", "https://firebasestorage.googleapis.com/v0/b/zpi2017-77741.appspot.com/o/hala.jpeg?alt=media&token=a3ed343e-8f79-434a-9b67-2b72a20366a7", 51.106869, 17.077285));
        trips[0].addAttraction(new Atrakcja("Rynek", "Costam", "https://firebasestorage.googleapis.com/v0/b/zpi2017-77741.appspot.com/o/rynek.jpg?alt=media&token=283fa1ea-53e9-4181-899a-80700d75a1be", 51.110108, 17.032062));
        trips[0].addAttraction(new Atrakcja("Wrocławskie ZOO", "Costam", "https://firebasestorage.googleapis.com/v0/b/zpi2017-77741.appspot.com/o/afrykarium.jpg?alt=media&token=7f4bb4aa-ba1a-42d6-9297-92c65f50c5bb", 51.104389, 17.075356));

        for (BaseTrip trip : trips) {
            mDatabase.child("baseTrips").push().setValue(trip);
        }
    }

    private void addPrzykladoweWycieczki() {
        BaseTrip[] trips;
        String[] zdjecia=new String[]{
                "https://firebasestorage.googleapis.com/v0/b/zpi2017-77741.appspot.com/o/afrykarium.jpg?alt=media&token=7f4bb4aa-ba1a-42d6-9297-92c65f50c5bb",
                "https://firebasestorage.googleapis.com/v0/b/zpi2017-77741.appspot.com/o/hala.jpeg?alt=media&token=a3ed343e-8f79-434a-9b67-2b72a20366a7",
                "https://firebasestorage.googleapis.com/v0/b/zpi2017-77741.appspot.com/o/ogrod.jpg?alt=media&token=1e7154bc-f65f-44cc-9e8e-68e40620c0ee",
                "https://firebasestorage.googleapis.com/v0/b/zpi2017-77741.appspot.com/o/rynek.jpg?alt=media&token=283fa1ea-53e9-4181-899a-80700d75a1be",
                "https://firebasestorage.googleapis.com/v0/b/zpi2017-77741.appspot.com/o/wieza.jpg?alt=media&token=864e564e-bff4-4e1f-9820-f0d0cc7c9c7c",

        };
        trips = new BaseTrip[3];
        trips[0] = new BaseTrip("Najlepsze atrakce", "https://firebasestorage.googleapis.com/v0/b/zpi2017-77741.appspot.com/o/hala.jpeg?alt=media&token=a3ed343e-8f79-434a-9b67-2b72a20366a7");
        trips[0].setRate(4);
        trips[1] = new BaseTrip("Wycieczka2", "https://firebasestorage.googleapis.com/v0/b/zpi2017-77741.appspot.com/o/hala.jpeg?alt=media&token=a3ed343e-8f79-434a-9b67-2b72a20366a7");
        trips[2] = new BaseTrip("Wycieczka3", "https://firebasestorage.googleapis.com/v0/b/zpi2017-77741.appspot.com/o/hala.jpeg?alt=media&token=a3ed343e-8f79-434a-9b67-2b72a20366a7");

        trips[0].addAttraction(new Atrakcja("Hala stulecia","Hala widowiskowo-sportowa znajdująca się we Wrocławiu, na osiedlu Zalesie, w parku Szczytnickim. Wzniesiona w latach 1911–1913 według projektu Maxa Berga, w stylu ekspresjonistycznym.",
                zdjecia[1], 51.106869, 17.077285));
        trips[0].addAttraction(new Atrakcja("Rynek","Sredniowieczny plac targowy we Wrocławiu, obecnie centralna część strefy pieszej. Stanowi prostokąt o wymiarach 213 na 178 m, oraz powierzchni 3,8 ha. Jest to jeden z największych rynków staromiejskich Europy, z największymi ratuszami w Polsce. ",
                zdjecia[3], 51.109454, 17.031332));
        trips[0].addAttraction(new Atrakcja("Wrocławskie ZOO","Ogród zoologiczny znajdujący się przy ul. Wróblewskiego 1–5 we Wrocławiu, otwarty 10 lipca 1865. Jest najstarszym na obecnych ziemiach polskich ogrodem zoologicznym w Polsce. Powierzchnia ogrodu to 33 hektary.",
                zdjecia[0], 51.104126, 17.074197));

        for (BaseTrip trip : trips) {
            mDatabase.child("baseTrips").push().setValue(trip.toMap());
        }
    }

    private void addAtrakcje() {

        List<Atrakcja> myDataset=new ArrayList<>();;
        String[] zdjecia=new String[]{
                "https://firebasestorage.googleapis.com/v0/b/zpi2017-77741.appspot.com/o/afrykarium.jpg?alt=media&token=7f4bb4aa-ba1a-42d6-9297-92c65f50c5bb",
                "https://firebasestorage.googleapis.com/v0/b/zpi2017-77741.appspot.com/o/hala.jpeg?alt=media&token=a3ed343e-8f79-434a-9b67-2b72a20366a7",
                "https://firebasestorage.googleapis.com/v0/b/zpi2017-77741.appspot.com/o/ogrod.jpg?alt=media&token=1e7154bc-f65f-44cc-9e8e-68e40620c0ee",
                "https://firebasestorage.googleapis.com/v0/b/zpi2017-77741.appspot.com/o/rynek.jpg?alt=media&token=283fa1ea-53e9-4181-899a-80700d75a1be",
                "https://firebasestorage.googleapis.com/v0/b/zpi2017-77741.appspot.com/o/wieza.jpg?alt=media&token=864e564e-bff4-4e1f-9820-f0d0cc7c9c7c",

        };

        Atrakcja atrakcja=new Atrakcja("Wrocławskie ZOO","Ogród zoologiczny znajdujący się przy ul. Wróblewskiego 1–5 we Wrocławiu, otwarty 10 lipca 1865. Jest najstarszym na obecnych ziemiach polskich ogrodem zoologicznym w Polsce. Powierzchnia ogrodu to 33 hektary.",
                zdjecia[0], 51.104126, 17.074197);
        myDataset.add(atrakcja);

        atrakcja=new Atrakcja("Hala stulecia","Hala widowiskowo-sportowa znajdująca się we Wrocławiu, na osiedlu Zalesie, w parku Szczytnickim. Wzniesiona w latach 1911–1913 według projektu Maxa Berga, w stylu ekspresjonistycznym.",
                zdjecia[1], 51.106869, 17.077285);
        myDataset.add(atrakcja);

        atrakcja=new Atrakcja("Ogród japoński","Znajduje się w Parku Szczytnickim w Śródmieściu Wrocławia.Ogród japoński został założony w latach 1909-1913 wokół dawnego stawu Ludwiga Theodora Moritza-Eichborna w obrębie obecnego Parku Szczytnickiego.",
                zdjecia[2], 51.109937, 17.080077);
        myDataset.add(atrakcja);

        atrakcja=new Atrakcja("Rynek","Sredniowieczny plac targowy we Wrocławiu, obecnie centralna część strefy pieszej. Stanowi prostokąt o wymiarach 213 na 178 m, oraz powierzchni 3,8 ha. Jest to jeden z największych rynków staromiejskich Europy, z największymi ratuszami w Polsce. ",
                zdjecia[3], 51.109454, 17.031332);
        myDataset.add(atrakcja);

        atrakcja=new Atrakcja("Wieża ciśnień","Historyzująca wieża ciśnień o wysokości całkowitej 62 m, zlokalizowana na osiedlu Borek we Wrocławiu. Oficjalny adres wieży to: ul. Sudecka 125a, jednak znajduje się ona na pasie rozdzielającym jezdnie al. Wiśniowej w ciągu obwodnicy śródmiejskiej. ",
                zdjecia[4], 51.085329, 17.017575);
        myDataset.add(atrakcja);

        DatabaseReference mAtrakcje = mDatabase.child("places");
        DatabaseReference mLokacje = mDatabase.child("locations");

        for (Atrakcja at : myDataset) {
            String key = mAtrakcje.push().getKey();
            mAtrakcje.child(key).setValue(at);
            mLokacje.child(key).child("latitude").setValue(at.getLatitude());
            mLokacje.child(key).child("longitude").setValue(at.getLongitude());
            mLokacje.child(key).child("nazwa").setValue(at.getNazwa());
        }
    }

    private void modifyCategories() {
        final ArrayList<Category> cats = new ArrayList<>();

        DatabaseReference mCategories = mDatabase.child("categories");
        mCategories.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot category : dataSnapshot.getChildren()) {
                    cats.add(category.getValue(Category.class));
                }
                for (Category c : cats) {
                    mDatabase.child("cattest").child(c.getId()).setValue(c);
                }
            Toast.makeText(MainActivity.this, "Tu Też Pojszło", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Cos sie zjebalo", Toast.LENGTH_LONG).show();
            }
        });
    }
}

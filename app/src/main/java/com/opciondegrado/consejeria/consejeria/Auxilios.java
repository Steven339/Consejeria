package com.opciondegrado.consejeria.consejeria;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Auxilios extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Auxilio> auxilios;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_auxilios);
        Toolbar toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);
        recyclerView = findViewById (R.id.auxilios);
        recyclerView.setLayoutManager (new LinearLayoutManager (this));
        auxilios = new ArrayList<> ();
        FirebaseDatabase database = FirebaseDatabase.getInstance ();
        adapter = new Adapter (auxilios);
        recyclerView.setAdapter (adapter);
        database.getReference ().child ("Auxilios").addValueEventListener (new ValueEventListener ( ) {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                auxilios.removeAll (auxilios);
                for (DataSnapshot snapshot:
                     dataSnapshot.getChildren ()) {
                    Auxilio aux = snapshot.getValue (Auxilio.class);
                    auxilios.add (aux);
                }
                adapter.notifyDataSetChanged ();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}

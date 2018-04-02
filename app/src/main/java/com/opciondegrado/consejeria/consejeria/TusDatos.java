package com.opciondegrado.consejeria.consejeria;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

public class TusDatos extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private TextView nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_tus_datos);
        nombre = findViewById (R.id.Nombre);

    }
}

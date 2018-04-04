package com.opciondegrado.consejeria.consejeria;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

public class TusDatos extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private TextView nombre;
    private EditText correo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_tus_datos);
        nombre = findViewById (R.id.Nombre);
        correo = findViewById (R.id.ci);
        Intent i = getIntent();
        Usuario usuario = (Usuario) i.getSerializableExtra("Usuario");

        nombre.setText (usuario.getNombre ());
        correo.setText (usuario.getCorreo ());


    }
}

package com.opciondegrado.consejeria.consejeria;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TusDatos extends AppCompatActivity {
    private TextView nombre;
    private EditText correo,documento,fechanac,ciudadnac,genero,edad,direccion,ciudad,fijo,celular,estrato,nacionalidad,rh;
    private Button button;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;
    private Usuario usuario;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_tus_datos);
        firebaseAuth = FirebaseAuth.getInstance ();
        final FirebaseUser user = firebaseAuth.getCurrentUser ();
        mDatabase = database.child("Users").child(user.getUid());
        // se declaran los elementos
        button = findViewById (R.id.button);
        nombre = findViewById (R.id.Nombre);
        correo = findViewById (R.id.ci);
        documento = findViewById (R.id.nd);
        fechanac = findViewById (R.id.fcn);
        ciudadnac = findViewById (R.id.cn);
        genero = findViewById (R.id.gen);
        edad = findViewById (R.id.edad);
        direccion = findViewById (R.id.dr);
        ciudad = findViewById (R.id.cl);
        fijo = findViewById (R.id.tf);
        celular = findViewById (R.id.tc);
        estrato = findViewById (R.id.est);
        nacionalidad = findViewById (R.id.nac);
        rh  = findViewById (R.id.rh);
        // se genera el intent y se extraen los datos serializados
        Intent i = getIntent();
        usuario = (Usuario) i.getSerializableExtra("Usuario");
        // se asigna los valores si los tiene guardados
        nombre.setText (usuario.getNombre ());
        correo.setText (usuario.getCorreo ());

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                documento.setText (dataSnapshot.child ("documento").getValue ().toString ());
                fechanac.setText (dataSnapshot.child ("fechanac").getValue ().toString ());
                ciudadnac.setText (dataSnapshot.child ("ciudadnac").getValue ().toString ());
                genero.setText (dataSnapshot.child ("genero").getValue ().toString ());
                edad.setText (dataSnapshot.child ("edad").getValue ().toString ());
                direccion.setText (dataSnapshot.child ("direccion").getValue ().toString ());
                ciudad.setText (dataSnapshot.child ("ciudad").getValue ().toString ());
                estrato.setText (dataSnapshot.child ("estrato").getValue ().toString ());
                nacionalidad.setText (dataSnapshot.child ("nacionalidad").getValue ().toString ());
                rh.setText (dataSnapshot.child ("rh").getValue ().toString ());
                fijo.setText (dataSnapshot.child ("fijo").getValue ().toString ());
                celular.setText (dataSnapshot.child ("celular").getValue ().toString ());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabase.addValueEventListener(postListener);

        button.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                usuario.setDocumento (documento.getText ().toString ());
                usuario.setFechanac (fechanac.getText ().toString ());
                usuario.setCiudadnac (ciudadnac.getText ().toString ());
                usuario.setGenero (genero.getText ().toString ());
                usuario.setEdad (edad.getText ().toString ());
                usuario.setDireccion (direccion.getText ().toString ());
                usuario.setCiudad (ciudad.getText ().toString ());
                usuario.setFijo (fijo.getText ().toString ());
                usuario.setCelular (celular.getText ().toString ());
                usuario.setEstrato (estrato.getText ().toString ());
                usuario.setNacionalidad (nacionalidad.getText ().toString ());
                usuario.setRh (rh.getText ().toString ());
                mDatabase.setValue (usuario);
            }
        });


    }
}

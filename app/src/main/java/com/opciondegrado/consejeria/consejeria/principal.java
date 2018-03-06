package com.opciondegrado.consejeria.consejeria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_principal);
        TextView texto = findViewById(R.id.texto);

        Bundle objeto = getIntent().getExtras();
        user usuario = null;
        if(objeto != null){
            usuario = (user) objeto.getSerializable("usuario");
            texto.setText ("Hola, "+usuario.getNombres ());
        }
    }
}

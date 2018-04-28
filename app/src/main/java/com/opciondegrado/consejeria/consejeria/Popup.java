package com.opciondegrado.consejeria.consejeria;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Popup extends AppCompatActivity {

    private TextView titulo,descripcion;
    private ImageView close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_popup);
        Intent intent = getIntent ();
        Auxilio auxilio = (Auxilio) intent.getSerializableExtra("Auxilio");
        titulo = findViewById (R.id.pop_titulo);
        descripcion = findViewById (R.id.pop_descripcion);
        close = findViewById (R.id.pop_close);
        titulo.setText (auxilio.getTitulo ());
        descripcion.setText (auxilio.getDescripcion ());

        close.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext (),Auxilios.class);
                intent.addFlags ( Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity (intent);
            }
        });
    }
}

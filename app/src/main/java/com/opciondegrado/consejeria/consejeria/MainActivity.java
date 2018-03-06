package com.opciondegrado.consejeria.consejeria;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView mTextView = findViewById(R.id.textView);
        mTextView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        final Intent principal = new Intent(this,principal.class);
        EditText correo = findViewById(R.id.correo);
        EditText pass = findViewById(R.id.pass);
        String user = correo.getText().toString();
        String password = pass.getText().toString();
        if(user.equals("")){
            Toast.makeText(getApplicationContext(),"Ingrese el correo electrónico",Toast.LENGTH_LONG).show();
            return;
        }
        if(password.equals("")){
            Toast.makeText(getApplicationContext(),"Ingrese la contraseña",Toast.LENGTH_LONG).show();
            return;
        }

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.0.19/uniminuto/sesion.php?correo="+correo.getText().toString()+"&pass="+pass.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(!response.equals("[]")){
                            Gson gson = new Gson();
                            user u = gson.fromJson(response, user.class);
                            //Bundle bundle = new Bundle();
                            //bundle.putSerializable ("usuario", (Serializable) u);
                            //principal.putExtras(bundle);
                            startActivity(principal);
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
            }
        });
        queue.add(stringRequest);
    }
}

package com.opciondegrado.consejeria.consejeria;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class InterfazPrinc extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private LinearLayout datos;
    GoogleApiClient googleApiClient;
    private FirebaseAuth firebaseAuth;

    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;
    private DatabaseReference mDatabase;
    private Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_princ);

        Toolbar toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);

        datos = findViewById(R.id.tus_datos);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder (GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail ()
                .build ();


        googleApiClient = new GoogleApiClient.Builder (this)
                .enableAutoManage (this,this)
                .addApi (Auth.GOOGLE_SIGN_IN_API,gso)
                .build ();

        firebaseAuth = FirebaseAuth.getInstance ();
        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener ( ) {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser ();

                if(user == null){
                    goLoginScreen();
                }else{

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    mDatabase = database.getReference().child("Users").child(user.getUid());
                    GoogleSignInAccount acc = GoogleSignIn.getLastSignedInAccount(getApplicationContext ());
                    String nombre = acc.getDisplayName ();
                    String email = acc.getEmail ();
                    Map newPost = new HashMap();
                    newPost.put ("Nombre",nombre);
                    newPost.put ("Correo",email);
                    mDatabase.setValue (newPost);
                    usuario = new Usuario (nombre,email);

                }
            }
        };

        datos.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext (),TusDatos.class);
                intent.putExtra ("Usuario", (Serializable) usuario);
                intent.addFlags ( Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity (intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate (R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId ()){
            case R.id.cerrar:
                firebaseAuth.signOut ();
                Auth.GoogleSignInApi.revokeAccess (googleApiClient).setResultCallback (new ResultCallback<Status> ( ) {
                    @Override
                    public void onResult(@NonNull Status status) {
                        if(status.isSuccess ()){
                            goLoginScreen ();
                        }else{
                            Toast.makeText (getApplicationContext (),"No se pudo cerrar sesion",Toast.LENGTH_SHORT).show ();
                        }
                    }
                });
                break;
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart ( );
        firebaseAuth.addAuthStateListener (firebaseAuthStateListener);
    }

    private void goLoginScreen() {
        Intent intent = new Intent (this,MainActivity.class);
        intent.addFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity (intent);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStop() {
        super.onStop ( );
        if(firebaseAuthStateListener != null){
            firebaseAuth.removeAuthStateListener (firebaseAuthStateListener);
        }
    }
}

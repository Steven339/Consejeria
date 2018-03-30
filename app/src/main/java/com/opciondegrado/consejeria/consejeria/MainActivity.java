package com.opciondegrado.consejeria.consejeria;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient googleApiClient;
    private SignInButton signInButton;
    private ProgressBar progressBar;
    public static final int SIGN_IN_CODE = 784;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_main);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken (getString (R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder (this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        signInButton = findViewById (R.id.signInButton);
        signInButton.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,SIGN_IN_CODE);
            }
        });
        firebaseAuth = FirebaseAuth.getInstance ();
        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener ( ) {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser ();
                if(user != null){
                    goMainScreen ();
                }
            }
        };
        progressBar = findViewById (R.id.progressBar);

    }
    public String getEmailDomain(String someEmail)
    {
        return  someEmail.substring(someEmail.indexOf("@") + 1);
    }
    @Override
    protected void onStart() {
        super.onStart ( );
        firebaseAuth.addAuthStateListener (firebaseAuthStateListener);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult (requestCode, resultCode, data);
        if(requestCode == SIGN_IN_CODE){
            GoogleSignInResult res =  Auth.GoogleSignInApi.getSignInResultFromIntent (data);
            handleSignInResult(res);
        }
    }

    private void handleSignInResult(GoogleSignInResult res) {
        if(res.isSuccess ()){
            firebaseAuthWithGoogle(res.getSignInAccount ());
        }else{
            Toast.makeText (this, getString(R.string.not_log_in), Toast.LENGTH_SHORT).show ( );
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount signInAccount) { ;
        String domain = getEmailDomain(signInAccount.getEmail ());
        if(domain.equals ("uniminuto.edu.co")){
            progressBar.setVisibility (View.VISIBLE);
            signInButton.setVisibility (View.INVISIBLE);
            AuthCredential credential = GoogleAuthProvider.getCredential (signInAccount.getIdToken (),null);
            firebaseAuth.signInWithCredential (credential).addOnCompleteListener (this, new OnCompleteListener<AuthResult> ( ) {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressBar.setVisibility (View.INVISIBLE);
                    signInButton.setVisibility (View.VISIBLE);
                    if(!task.isSuccessful ()){
                        Toast.makeText (getApplicationContext (),"No se pudo autenticar con la base de datos",Toast.LENGTH_SHORT).show ();
                    }
                }
            });
        }else{
            Auth.GoogleSignInApi.revokeAccess (googleApiClient).setResultCallback (new ResultCallback<Status> ( ) {
                @Override
                public void onResult(@NonNull Status status) {
                    if(status.isSuccess ()){
                        progressBar.setVisibility (View.INVISIBLE);
                        signInButton.setVisibility (View.VISIBLE);
                        Toast.makeText (getApplicationContext (),"No se pudo iniciar sesión",Toast.LENGTH_SHORT).show ();
                    }
                }
            });
        }

    }


    private void goMainScreen() {
        Intent intent = new Intent (this,InterfazPrinc.class);
        intent.addFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity (intent);
    }

    @Override
    protected void onStop() {
        super.onStop ( );
        if(firebaseAuthStateListener != null){
            firebaseAuth.removeAuthStateListener (firebaseAuthStateListener);
        }
    }
}

package com.mwalagho.ferdinand.cosocial.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.mwalagho.ferdinand.cosocial.R;
import com.mwalagho.ferdinand.cosocial.UsersActivity;


import butterknife.BindView;
import butterknife.ButterKnife;
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

//    @BindView(R.id.emailEditText) EditText mEmail;
//    @BindView(R.id.passwordEditText) EditText mPass;
    @BindView(R.id.buttonClick) Button btnMaps;
    public static final String TAG = LoginActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 9001;
    private FirebaseAuth mAuth;
   GoogleSignInClient mGoogleSignInClient;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        Log.i(TAG,"HEEEEEEEEEEEEEEEEEEEEEERE" +FirebaseAuth.getInstance().toString());

        findViewById(R.id.googleBtn).setOnClickListener(this);


// Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        SignInButton signInButton = findViewById(R.id.googleBtn);
        signInButton.setSize(SignInButton.SIZE_ICON_ONLY);

        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null){
            Log.d(TAG,"Currently signed in as" + currentUser.getDisplayName());
            Toast.makeText(this,"Currently logged in:" + currentUser.getEmail(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task= GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Toast.makeText(this,"Google Sign In Succeeded",Toast.LENGTH_LONG).show();
                firebaseAuthWithGoogle(account);

            } catch (ApiException e){
                Log.w(TAG,"Google sign in failed",e);
                Toast.makeText(this,"Google sign in failed" + e ,Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if(i == R.id.googleBtn){
            signIn();
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct){
        Log.d(TAG,"FIREBASE AUTH WITH GOOGLE" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(),null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful()){
                FirebaseUser user = mAuth.getCurrentUser();
                Log.d(TAG,"signInSucces: user" + user.getEmail());
                Toast.makeText(LoginActivity.this,"Succesfull firebase authentication",Toast.LENGTH_LONG).show();
            }    else {
                Log.w(TAG,"signInFailedWithEXCEPTION",task.getException());
                Toast.makeText(LoginActivity.this,"Firebase Authentication Failed",Toast.LENGTH_LONG).show();
            }
            }
        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
        Intent intent = new Intent(LoginActivity.this, UsersActivity.class);
        startActivity(intent);
    }







}

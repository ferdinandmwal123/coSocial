package com.mwalagho.ferdinand.cosocial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.GestureDetector;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.*;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.cName) EditText name;
    @BindView(R.id.button3) Button mRegister;
    public static final String TAG = MainActivity.class.getSimpleName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getNames = name.getText().toString();
                ((EditText) findViewById(R.id.cName)).setText(" ");

                Intent intent = new Intent(MainActivity.this,UsersActivity.class);
                intent.putExtra("names",getNames);
                startActivity(intent);


                Toast.makeText(MainActivity.this,"Registered Successfully!",Toast.LENGTH_LONG).show();
                Log.i(TAG,"User saved");
            }
        });

    }


}

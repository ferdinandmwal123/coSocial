package com.mwalagho.ferdinand.cosocial;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.mwalagho.ferdinand.cosocial.ui.MapsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UsersActivity extends AppCompatActivity {

    @BindView(R.id.user)   TextView mTextView;
    @BindView(R.id.button) Button buttonGrant;
    public static final String TAG = UsersActivity.class.getSimpleName();




    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String user = intent.getStringExtra("names");
       mTextView.setText("Welcome " + user);
        Log.i(TAG,"Created user");



        buttonGrant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1= new Intent(UsersActivity.this, MapsActivity.class);
                startActivity(intent1);
            }
        });
    }
}

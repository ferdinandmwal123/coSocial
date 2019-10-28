package com.mwalagho.ferdinand.cosocial;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mwalagho.ferdinand.cosocial.ui.MapsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UsersActivity extends AppCompatActivity {


    @BindView(R.id.btnmap) Button btnMap;
    @BindView(R.id.youtubebtn) Button btnYoutube;
    @BindView(R.id.btn_quotes) Button btnQuote;
    public static final String TAG = UsersActivity.class.getSimpleName();




    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        ButterKnife.bind(this);

//        Intent intent = getIntent();
//        String user = intent.getStringExtra("names");
//       mTextView.setText("Welcome " + user);
//        Log.i(TAG,"Created user");



        btnQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UsersActivity.this,QuoteActivity.class);
                startActivity(intent);
            }
        });

      btnYoutube.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(UsersActivity.this, YoutubeActivity.class);
              startActivity(intent);
          }
      });



        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1= new Intent(UsersActivity.this, MapsActivity.class);
                startActivity(intent1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.main_menu,menu);
        Log.i(TAG,"Main Menu created");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(UsersActivity.this, PostActivity.class));
        return super.onOptionsItemSelected(item);

    }

}

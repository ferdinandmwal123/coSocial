package com.mwalagho.ferdinand.cosocial;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
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



        btnQuote.setOnClickListener(v -> {
            Intent intent = new Intent(UsersActivity.this,QuoteActivity.class);
            startActivity(intent);
            Animatoo.animateFade(UsersActivity.this);
        });

      btnYoutube.setOnClickListener(v -> {
          Intent intent = new Intent(UsersActivity.this, YoutubeActivity.class);
          startActivity(intent);
          Animatoo.animateInAndOut(UsersActivity.this);
      });



        btnMap.setOnClickListener(v -> {
            Intent intent1= new Intent(UsersActivity.this, MapsActivity.class);
            startActivity(intent1);
            Animatoo.animateWindmill(UsersActivity.this);
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

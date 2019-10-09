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
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{

    @BindView(R.id.button3) Button mRegister;
    public GestureDetector gestureDetector;
    public static final String TAG = MainActivity.class.getSimpleName();
    ArrayList<String> names = new ArrayList<>();
    @BindView(R.id.cName) EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.gestureDetector = new GestureDetector(this,this);
        gestureDetector.setOnDoubleTapListener(this);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getNames = name.getText().toString();
                ArrayList<String> names = new ArrayList<>();
                names.add(getNames);
                ((EditText) findViewById(R.id.cName)).setText(" ");

                Intent intent = new Intent(MainActivity.this,CompaniesActivity.class);
                intent.putExtra("names",names);
                startActivity(intent);


                Toast.makeText(MainActivity.this,"Registered Successfully!",Toast.LENGTH_LONG).show();
                System.out.println(names);
                Log.i(TAG,"User saved");
            }
        });

    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Toast.makeText(MainActivity.this,"Welcome! \uD83D\uDE0A",Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Toast.makeText(MainActivity.this,"We need your details \uD83D\uDE0A",Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}

package com.mwalagho.ferdinand.cosocial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button3) Button mRegister;
//    @BindView(R.id.cList) ListView mListView
    public static final String TAG = MainActivity.class.getSimpleName();
    ArrayList<String> names = new ArrayList<>();
    @BindView(R.id.cName) EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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
                Log.i(TAG,"User saved");
            }
        });

    }
}

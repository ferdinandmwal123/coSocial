package com.mwalagho.ferdinand.cosocial;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompaniesActivity extends AppCompatActivity {

    @BindView(R.id.cList) ListView mListView;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companies);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        ArrayList<String> companies = intent.getStringArrayListExtra("names");
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1,companies);
        mListView.setAdapter(adapter);
    }
}

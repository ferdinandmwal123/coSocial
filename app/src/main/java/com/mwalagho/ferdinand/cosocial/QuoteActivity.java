package com.mwalagho.ferdinand.cosocial;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.mwalagho.ferdinand.cosocial.Constants.QUOTE_BASE_URL;

public class QuoteActivity extends AppCompatActivity {
    @BindView(R.id.quotes_view)
    RecyclerView mQuoteRecyclerView;
    private QuoteAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public static final String TAG = QuoteActivity.class.getSimpleName();
    ArrayList<Quote> activityQuoteList;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("Activity created", "Activity has successfully been created");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);
        ButterKnife.bind(this);

        layoutManager = new LinearLayoutManager(this);

        mQuoteRecyclerView.setLayoutManager(layoutManager);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(QUOTE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        QuoteApi quoteApi = retrofit.create(QuoteApi.class);

        Call<QuoteResult> call = quoteApi.getQuotes();

        call.enqueue(new Callback<QuoteResult>() {
            @Override
            public void onResponse(Call<QuoteResult> call, Response<QuoteResult> response) {
                Log.i(TAG, "check the response from the api" + response);
                if (!response.isSuccessful()) {
                    Toast.makeText(QuoteActivity.this, "Code: " + response.code(), Toast.LENGTH_LONG).show();//Return http code e.g. 404
                    return;
                }
                QuoteResult quoteResult = response.body();


                if (quoteResult != null) {
                    activityQuoteList = quoteResult.results;
                    Log.i("quotesList in activity", String.valueOf(activityQuoteList));
                    mAdapter =new QuoteAdapter(activityQuoteList);
                    mAdapter.notifyDataSetChanged();
                }

                mQuoteRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<QuoteResult> call, Throwable t) {
                Log.i(TAG, "check the response from the api" + t.getMessage());
                Toast.makeText(QuoteActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }
}

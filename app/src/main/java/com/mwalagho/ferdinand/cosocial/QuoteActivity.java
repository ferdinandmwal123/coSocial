package com.mwalagho.ferdinand.cosocial;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.mwalagho.ferdinand.cosocial.Constants.QUOTE_BASE_URL;

public class QuoteActivity extends AppCompatActivity {
    @BindView(R.id.text_view_result) TextView mTextView;
    public static final String TAG = QuoteActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);
        ButterKnife.bind(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(QUOTE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        QuoteApi quoteApi = retrofit.create(QuoteApi.class);

        Call<QuoteResult> call = quoteApi.getQuotes();

        call.enqueue(new Callback<QuoteResult>() {
            @Override
            public void onResponse(Call<QuoteResult> call, Response<QuoteResult> response) {
                Log.i(TAG,"check the response from the api" + response);
                if(!response.isSuccessful()){
                    mTextView.setText("Code: " + response.code());//Return http code e.g. 404
                    return;
                }
                QuoteResult quotes = response.body();
            }

            @Override
            public void onFailure(Call<QuoteResult> call, Throwable t) {
                Log.i(TAG,"check the response from the api" + t.getMessage());
                mTextView.setText(t.getMessage());
            }
        });

    }
}

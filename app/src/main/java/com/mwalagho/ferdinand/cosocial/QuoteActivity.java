package com.mwalagho.ferdinand.cosocial;

import android.os.Bundle;
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

        Call<List<Quote>> call = quoteApi.getQuotes();

        call.enqueue(new Callback<List<Quote>>() {
            @Override
            public void onResponse(Call<List<Quote>> call, Response<List<Quote>> response) {
                if(!response.isSuccessful()){
                    mTextView.setText("Code: " + response.code());//Return http code e.g. 404
                    return;
                }
                List<Quote> quotes = response.body();

                for(Quote quote : quotes){
                    String content = "";
                    content += "ID " + quote.get_id() + "\n";
                    content += "Quote : " + quote.getQuote() + "\n";
                    content+= "Author :" + quote.getAuthor() + "\n\n";

                    mTextView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Quote>> call, Throwable t) {
                mTextView.setText(t.getMessage());
            }
        });

    }
}

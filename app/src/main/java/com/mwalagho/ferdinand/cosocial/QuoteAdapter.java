package com.mwalagho.ferdinand.cosocial;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.QuotesViewHolder>{

    private List<Quote> quotes;


    public static class QuotesViewHolder extends RecyclerView.ViewHolder {

        public View quotesView;

        public QuotesViewHolder(View v) {
            super(v);
            quotesView = v;
        }
    }

    // constructor
    public QuoteAdapter(List<Quote> quotes) {
        this.quotes = quotes;
        Log.i("quotesList in quotes", String.valueOf(quotes));
    }


    @NonNull
    @Override
    public QuotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_quote_layout, parent, false);
        QuotesViewHolder vh = new QuotesViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull QuotesViewHolder holder, int position) {

        TextView author = holder.quotesView.findViewById(R.id.text_author);
        TextView quote = holder.quotesView.findViewById(R.id.text_quote);

        author.setText(quotes.get(position).getAuthor());
        quote.setText(quotes.get(position).getQuote());

    }

    @Override
    public int getItemCount() {
        if(quotes == null) {
            return 0;
        }
        return quotes.size();
    }

    public List<Quote> getQuotes() {
        return quotes;
    }
}

package com.mwalagho.ferdinand.cosocial;

import com.google.gson.annotations.SerializedName;

public class Quote {

    private String _id;
    private String author;
    @SerializedName("content")
    private String quote;

    public String get_id() {
        return _id;
    }

    public String getAuthor() {
        return author;
    }

    public String getQuote() {
        return quote;
    }
}
package com.mwalagho.ferdinand.cosocial;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuoteApi  {

    @GET("quotes")
    Call<QuoteResult> getQuotes();

}

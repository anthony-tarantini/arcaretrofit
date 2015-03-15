package io.tony.arcaretrofit.services;

import java.util.List;

import io.tony.arcaretrofit.models.Card;
import retrofit.http.GET;

public interface RetroApiService {
    @GET("/cards/?colors=red,black,blue")
    List<Card> getCardList();
}

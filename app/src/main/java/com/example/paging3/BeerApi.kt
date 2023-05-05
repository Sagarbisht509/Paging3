package com.example.paging3

import com.example.paging3.models.Beer
import retrofit2.http.GET
import retrofit2.http.Query

interface BeerApi {

    @GET("beers")
    suspend fun getBeers(
        @Query("page") page: Int,
        @Query("per_page") pageCount: Int
    ) : List<Beer>
}
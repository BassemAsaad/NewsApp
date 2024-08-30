package com.example.session11_newsapp.api

import com.example.session11_newsapp.api.Model.NewsResponse
import com.example.session11_newsapp.api.Model.SourceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
interface WebServices {
    @GET("v2/top-headlines/sources")
    fun getSources(
        @Query("apiKey") apiKey:String
        , @Query("category") category:String
    ): Call<SourceResponse>


    @GET("v2/everything")
    fun getNews(
        @Query("apiKey") apiKey:String
        , @Query("sources") sources:String
    ):Call<NewsResponse>
}
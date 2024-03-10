package com.example.session11_newsapp.API

import com.example.session11_newsapp.API.Model.NewsResponse
import com.example.session11_newsapp.API.Model.SourceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
interface WebServices {
    @GET("v2/top-headlines/sources")
    fun getSources(@Query("apiKey") api_Key:String) : Call<SourceResponse>

    @GET("v2/everything")
    fun getNews(
        @Query("apiKey") api_Key:String, @Query("sources") sources:String
    ):Call<NewsResponse>
}
package com.example.session11_newsapp.API

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class API_Manager {

    //to make it static
    companion object{
        private var retrofit : Retrofit?=null
        fun getInstance():Retrofit {

            if (retrofit==null){
                retrofit = Retrofit.Builder()
                    .baseUrl("https://newsapi.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }  else {
                return retrofit!!
            }
            return retrofit!!
        }

        fun getAPI():WebServices{
            return getInstance().create(WebServices::class.java)
        }


    }

}
package com.example.session11_newsapp.user_interface.news

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.session11_newsapp.api.API_Manager
import com.example.session11_newsapp.api.Model.ArticlesItem
import com.example.session11_newsapp.api.Model.NewsResponse
import com.example.session11_newsapp.api.Model.SourceResponse
import com.example.session11_newsapp.api.Model.SourcesItem
import com.example.session11_newsapp.Constants
import com.example.session11_newsapp.user_interface.category.Category_Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//class view model to do logic operations
//observable
class NewsVM :ViewModel(){
    //create variable of sources type
    val sourceLiveData = MutableLiveData<List<SourcesItem?>?>()
    //create variable of articles type
    val newsLiveData = MutableLiveData<List<ArticlesItem?>?>()
    //control progress bar
    val progressBar_Visable = MutableLiveData<Boolean>()

     fun getSources(category: Category_Data) {
         progressBar_Visable.value = true

        API_Manager.getAPI().getSources(Constants.ApiKey,category.id)
            .enqueue(object : Callback<SourceResponse> {
                //onFailure
                override fun onFailure(call: Call<SourceResponse>, t: Throwable) {
                    progressBar_Visable.value = false

                    Log.e("error",t.localizedMessage)
                }
                //onResponse
                override fun onResponse(call: Call<SourceResponse>,
                                        response: Response<SourceResponse>
                ) {
                    progressBar_Visable.value = false
                    //add response variable to add it later to TabLayout
                    sourceLiveData.value= response.body()?.sources
                }
            }//object end
            )//enqueue end
    }//function end


     fun getNews_BySources(source: SourcesItem) {
         progressBar_Visable.value = true

        API_Manager.getAPI().getNews(Constants.ApiKey,source.id?:"")
            .enqueue(object :Callback<NewsResponse>{
                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    progressBar_Visable.value = false
                    Log.e("error",t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    progressBar_Visable.value = false
                    //show it on recyclerview
                    newsLiveData.value = response.body()?.articles
                }
            })

    }


}
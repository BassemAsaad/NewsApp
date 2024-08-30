package com.example.session11_newsapp.user_interface.news

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.session11_newsapp.api.APIManager
import com.example.session11_newsapp.api.Model.ArticlesItem
import com.example.session11_newsapp.api.Model.NewsResponse
import com.example.session11_newsapp.api.Model.SourceResponse
import com.example.session11_newsapp.api.Model.SourcesItem
import com.example.session11_newsapp.Constants
import com.example.session11_newsapp.user_interface.category.CategoryModel
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
    val progressBarVisible = MutableLiveData<Boolean>()

     fun getSources(category: CategoryModel) {
         progressBarVisible.value = true

        APIManager.getAPI().getSources(Constants.API_KEY,category.id)
            .enqueue(object : Callback<SourceResponse> {
                //onFailure
                override fun onFailure(call: Call<SourceResponse>, t: Throwable) {
                    progressBarVisible.value = false

                    Log.e("error",t.localizedMessage!!.toString())
                }
                //onResponse
                override fun onResponse(call: Call<SourceResponse>,
                                        response: Response<SourceResponse>
                ) {
                    progressBarVisible.value = false
                    //add response variable to add it later to TabLayout
                    sourceLiveData.value= response.body()?.sources
                }
            }//object end
            )//enqueue end
    }//function end


     fun getNewsBySources(source: SourcesItem) {
         progressBarVisible.value = true

        APIManager.getAPI().getNews(Constants.API_KEY,source.id?:"")
            .enqueue(object :Callback<NewsResponse>{
                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    progressBarVisible.value = false
                    Log.e("error",t.localizedMessage!!.toString())
                }

                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    progressBarVisible.value = false
                    //show it on recyclerview
                    newsLiveData.value = response.body()?.articles
                }
            })

    }


}
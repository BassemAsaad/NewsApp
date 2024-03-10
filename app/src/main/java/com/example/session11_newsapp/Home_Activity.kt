package com.example.session11_newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.session11_newsapp.API.API_Manager
import com.example.session11_newsapp.Adapters.News_Adapter
import com.example.session11_newsapp.API.Model.NewsResponse
import com.example.session11_newsapp.API.Model.SourceResponse
import com.example.session11_newsapp.API.Model.SourcesItem
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home_Activity : AppCompatActivity() {

    lateinit var tabLayout:TabLayout
    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    val adapterNews =News_Adapter(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        init()
        getSources()
    }

    fun init(){
        tabLayout = findViewById(R.id.tap_layout)
        recyclerView = findViewById(R.id.recycler_view)
        progressBar = findViewById(R.id.progress_bar)

        recyclerView.adapter= adapterNews

    }

    private fun getSources() {
        progressBar.isVisible==true
        API_Manager.getAPI().getSources(Constants.ApiKey)
            .enqueue(object : Callback<SourceResponse>{
                //onFailure
                override fun onFailure(call: Call<SourceResponse>, t: Throwable) {
                    progressBar.isVisible==false
                    Log.e("error",t.localizedMessage)
                }
                //onResponce
                override fun onResponse(call: Call<SourceResponse>,
                                        response: Response<SourceResponse>) {
                    //add response to TabLayout
                    addSourcesToTabLayout(response.body()?.sources)
                }
            }//object end
            )//enqueue end
    }//function end

    private fun addSourcesToTabLayout(sources: List<SourcesItem?>?) {
        sources?.forEach {source->
            val tab = tabLayout.newTab()
            tab.setText(source?.name)
            tab.tag = source
            tabLayout.addTab(tab)
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val sourceName = tab?.tag as SourcesItem
                    getNews_BySources(sourceName)
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    val sourceName = tab?.tag as SourcesItem
                    getNews_BySources(sourceName)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    Log.e("tab",tab?.text.toString())
                }
            }//end of object
            )//end of addOnTabSelectedListener
        }//foreach
        tabLayout.getTabAt(0)?.select()
    }

    private fun getNews_BySources(source: SourcesItem) {
        progressBar.isVisible==true

        API_Manager.getAPI().getNews(Constants.ApiKey,source.id?:"")
            .enqueue(object :Callback<NewsResponse>{
                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    progressBar.isVisible==false
                    Log.e("error",t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    progressBar.isVisible==false
                    //show it on recyclerview
                    adapterNews.changeItem(response.body()?.articles)
                }
            })

    }

}
package com.example.session11_newsapp.User_Interface.News

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.session11_newsapp.API.API_Manager
import com.example.session11_newsapp.API.Model.NewsResponse
import com.example.session11_newsapp.API.Model.SourceResponse
import com.example.session11_newsapp.API.Model.SourcesItem
import com.example.session11_newsapp.Constants
import com.example.session11_newsapp.R
import com.example.session11_newsapp.User_Interface.Category.Category_Data
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class News_Fragment(val category:Category_Data) : Fragment() {

    lateinit var tabLayout:TabLayout
    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    val adapterNews = News_Adapter(null)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news  ,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }

    fun init(){
        tabLayout = requireView().findViewById(R.id.tap_layout)
        recyclerView = requireView().findViewById(R.id.recycler_view)
        progressBar = requireView().findViewById(R.id.progress_bar)
        getSources()
        recyclerView.adapter= adapterNews

    }

    private fun getSources() {
        progressBar.isVisible==true
        API_Manager.getAPI().getSources(Constants.ApiKey,category.id)
            .enqueue(object : Callback<SourceResponse>{
                //onFailure
                override fun onFailure(call: Call<SourceResponse>, t: Throwable) {
                    progressBar.isVisible==false
                    Log.e("error",t.localizedMessage)
                }
                //onResponse
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
package com.example.session11_newsapp.user_interface.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.session11_newsapp.api.Model.SourcesItem
import com.example.session11_newsapp.R
import com.example.session11_newsapp.databinding.FragmentNewsBinding
import com.example.session11_newsapp.user_interface.category.CategoryModel
import com.google.android.material.tabs.TabLayout

//view
//observe
class News_Fragment(val category:CategoryModel) : Fragment() {

    lateinit var dataBinding:FragmentNewsBinding
    val adapterNews = News_Adapter(null)

    lateinit var viewModel : NewsVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return inflater.inflate(R.layout.fragment_news  ,container,false)
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_news,container,false)
        return dataBinding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(NewsVM::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        subscribeToLiveData()
        viewModel.getSources(category)

    }

    private fun subscribeToLiveData() {
        viewModel.sourceLiveData.observe(viewLifecycleOwner, Observer{
            //do anything to ui

            // add response to tab layout
            addSourcesToTabLayout(it)
        }//end observer
        )//end

        viewModel.newsLiveData.observe(viewLifecycleOwner,Observer{
            //do anything to ui

            //put news in recycler view
            adapterNews.changeItem(it)
        }//end observer
        )//end

        viewModel.progressBar_Visable.observe(viewLifecycleOwner,Observer{
            dataBinding.progressBar.isVisible = it

        })
    }

    fun init(){

        dataBinding.recyclerView.adapter= adapterNews

    }


    private fun addSourcesToTabLayout(sources: List<SourcesItem?>?) {
        sources?.forEach {source->
            val tab = dataBinding.tapLayout.newTab()
            tab.setText(source?.name)
            tab.tag = source
            dataBinding.tapLayout.addTab(tab)
            dataBinding.tapLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val sourceName = tab?.tag as SourcesItem
                    viewModel.getNews_BySources(sourceName)
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    val sourceName = tab?.tag as SourcesItem
                    viewModel.getNews_BySources(sourceName)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    Log.e("tab",tab?.text.toString())
                }
            }//end of object
            )//end of addOnTabSelectedListener
        }//foreach
        dataBinding.tapLayout.getTabAt(0)?.select()
    }



}
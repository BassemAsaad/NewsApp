package com.example.session11_newsapp.user_interface.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.session11_newsapp.R

class CategoryFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category,container,false)
    }

    private lateinit var recyclerView: RecyclerView
    private val categoryList = listOf(
        CategoryModel("sports","Sports",R.drawable.ic_sports,R.color.red_light),
        CategoryModel("technology","Technology",R.drawable.ic_politics,R.color.blue),
        CategoryModel("health","Health",R.drawable.ic_health,R.color.pink),
        CategoryModel("business","Business",R.drawable.ic_bussines,R.color.brown),
        CategoryModel("general","General",R.drawable.ic_environment,R.color.blue_light),
        CategoryModel("science","Science",R.drawable.ic_science,R.color.yellow)
    )
    private var categoryAdapter =  CategoryAdapter(categoryList)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        recyclerView = requireView().findViewById(R.id.recycler_view_category)

        categoryAdapter.onItemClickListener = object : CategoryAdapter.OnItemClickListener{
            override fun onItemClick(pos: Int, category: CategoryModel) {
                //push News_Fragment
                onCategoryClickListener?.onCategoryClick(pos,category)
            }

        }

        recyclerView.adapter= categoryAdapter
    }

    var onCategoryClickListener:OnCategoryClickListener?=null
    interface OnCategoryClickListener{
        fun onCategoryClick(pos: Int, category: CategoryModel)
    }
}
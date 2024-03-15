package com.example.session11_newsapp.user_interface.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.session11_newsapp.R

class Category_Fragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category,container,false)
    }

    lateinit var recyclerView: RecyclerView
    val categoryList = listOf<CategoryModel>(
        CategoryModel("sports","Sports",R.drawable.ic_sports,R.color.red_light),
        CategoryModel("technology","Technology",R.drawable.ic_politics,R.color.blue),
        CategoryModel("health","Health",R.drawable.ic_health,R.color.pink),
        CategoryModel("business","Business",R.drawable.ic_bussines,R.color.brown),
        CategoryModel("general","General",R.drawable.ic_environment,R.color.blue_light),
        CategoryModel("science","Science",R.drawable.ic_science,R.color.yellow)
    )
    var category_Adapter =  Category_Adapter(categoryList)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init_View()
    }

    private fun init_View() {
        recyclerView = requireView().findViewById(R.id.recycler_view_category)

        category_Adapter.onItemClickListener = object : Category_Adapter.OnItemClickListener{
            override fun onItemClick(pos: Int, category: CategoryModel) {
                //push News_Fragment
                onCategoryClickListener?.onCategoryClick(pos,category)
            }

        }

        recyclerView.adapter= category_Adapter
    }

    var onCategoryClickListener:OnCategoryClickListener?=null
    interface OnCategoryClickListener{
        fun onCategoryClick(pos: Int, category: CategoryModel)
    }
}
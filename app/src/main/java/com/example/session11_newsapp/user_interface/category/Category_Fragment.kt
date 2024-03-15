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
    val categoryList = listOf<Category_Data>(
        Category_Data("sports","Sports",R.drawable.ic_sports,R.color.red_light),
        Category_Data("technology","Technology",R.drawable.ic_politics,R.color.blue),
        Category_Data("health","Health",R.drawable.ic_health,R.color.pink),
        Category_Data("business","Business",R.drawable.ic_bussines,R.color.brown),
        Category_Data("general","General",R.drawable.ic_environment,R.color.blue_light),
        Category_Data("science","Science",R.drawable.ic_science,R.color.yellow)
    )
    var category_Adapter =  Category_Adapter(categoryList)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init_View()
    }

    private fun init_View() {
        recyclerView = requireView().findViewById(R.id.recycler_view_category)

        category_Adapter.onItemClickListener = object : Category_Adapter.OnItemClickListener{
            override fun onItemClick(pos: Int, category: Category_Data) {
                //push News_Fragment
                onCategoryClickListener?.onCategoryClick(pos,category)
            }

        }

        recyclerView.adapter= category_Adapter
    }

    var onCategoryClickListener:OnCategoryClickListener?=null
    interface OnCategoryClickListener{
        fun onCategoryClick(pos: Int, category: Category_Data)
    }
}
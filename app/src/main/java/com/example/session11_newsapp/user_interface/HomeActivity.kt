package com.example.session11_newsapp.user_interface

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.session11_newsapp.R
import com.example.session11_newsapp.user_interface.news.NewsFragment
import com.example.session11_newsapp.user_interface.category.CategoryModel
import com.example.session11_newsapp.user_interface.category.CategoryFragment

class HomeActivity : AppCompatActivity(){
    private lateinit var icMenu : ImageView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var category: TextView

    private val categoryFragment = CategoryFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initView()
    }

    private fun initView() {
        drawerLayout = findViewById(R.id.drawer_layout)
        icMenu = findViewById(R.id.menu)
        category = findViewById(R.id.category_tv)

        icMenu.setOnClickListener{
            //open drawer
            drawerLayout.open()

        }
        category.setOnClickListener{
            //push fragment category
            pushFragment(categoryFragment)
        }

        pushFragment(categoryFragment)


        categoryFragment.onCategoryClickListener = object : CategoryFragment.OnCategoryClickListener{
            override fun onCategoryClick(pos: Int, category: CategoryModel) {
                pushFragment(NewsFragment(category),true)
            }
        }


    }


    @SuppressLint("SuspiciousIndentation")
    fun pushFragment(fragment: Fragment, addToBackStack:Boolean=false){
        val push = supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            if(addToBackStack){
                push.addToBackStack("name")
            }
            push.commit()

        drawerLayout.close()
    }
}
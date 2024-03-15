package com.example.session11_newsapp.user_interface

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.session11_newsapp.R
import com.example.session11_newsapp.user_interface.news.News_Fragment
import com.example.session11_newsapp.user_interface.category.Category_Data
import com.example.session11_newsapp.user_interface.category.Category_Fragment
import com.example.session11_newsapp.user_interface.settings.Settings_Fragment

class Home_Activity : AppCompatActivity(){
    private lateinit var ic_menu : ImageView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var category: TextView
    private lateinit var settings: TextView

    val categoryFragment = Category_Fragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        init_view()
    }

    private fun init_view() {
        drawerLayout = findViewById(R.id.drawer_layout)
        ic_menu = findViewById(R.id.menu)
        category = findViewById(R.id.category_tv)
        settings = findViewById(R.id.settings_tv)

        ic_menu.setOnClickListener{
            //open drawer
            drawerLayout.open()

        }
        category.setOnClickListener{
            //push fragment category
            pushFragment(Category_Fragment())
        }

        settings.setOnClickListener{
            //push fragment settings
            pushFragment(Settings_Fragment())
        }

        pushFragment(categoryFragment)


        categoryFragment.onCategoryClickListener = object : Category_Fragment.OnCategoryClickListener{
            override fun onCategoryClick(pos: Int, category: Category_Data) {
                pushFragment(News_Fragment(category),true)
            }
        }


    }


    fun pushFragment(fragment: Fragment, addtoBackStack:Boolean=false){
        val push = supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            if(addtoBackStack==true){
                push.addToBackStack("name")
            }
            push.commit()
        drawerLayout.close()
    }
}
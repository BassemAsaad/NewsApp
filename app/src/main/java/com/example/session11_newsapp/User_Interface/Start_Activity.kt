package com.example.session11_newsapp.User_Interface

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.session11_newsapp.R
import com.example.session11_newsapp.User_Interface.Category.Category_Fragment
import com.example.session11_newsapp.User_Interface.Settings.Settings_Fragment

class Start_Activity : AppCompatActivity() {

     lateinit var ic_menu : ImageView
     lateinit var drawerLayout: DrawerLayout
     lateinit var category: TextView
     lateinit var settings: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        init_view()
    }

     fun init_view() {
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

        pushFragment(Category_Fragment())

    }

    fun pushFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .commit()
        drawerLayout.close()
    }


}
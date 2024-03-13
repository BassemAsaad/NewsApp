package com.example.session11_newsapp.User_Interface.News

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.session11_newsapp.API.Model.ArticlesItem
import com.example.session11_newsapp.R

class News_Adapter(var listItems: List<ArticlesItem?>?) : RecyclerView.Adapter<News_Adapter.ViewHolder>() {

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val author:TextView=itemView.findViewById(R.id.author_item)
        val title:TextView=itemView.findViewById(R.id.title_item)
        val time:TextView=itemView.findViewById(R.id.publishTime_item)
        val img:ImageView=itemView.findViewById(R.id.img_item)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItems?.size?:0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItems?.get(position)
        holder.title.setText(item?.title)
        holder.author.setText(item?.author)
        holder.time.setText(item?.publishedAt)

        //Glide responsible to change from url to img
        Glide.with(holder.itemView).load(item?.urlToImage).into(holder.img)


    }

    fun changeItem(articles: List<ArticlesItem?>?) {
        listItems=articles
        notifyDataSetChanged()
    }

}
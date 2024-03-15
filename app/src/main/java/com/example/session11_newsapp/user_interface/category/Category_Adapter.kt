package com.example.session11_newsapp.user_interface.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.session11_newsapp.R
import com.google.android.material.card.MaterialCardView

class Category_Adapter (val categoryList: List<CategoryModel>): RecyclerView.Adapter<Category_Adapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val title : TextView = itemView.findViewById(R.id.title_card)
        val img : ImageView = itemView.findViewById(R.id.img_card)
        val materialCard : MaterialCardView = itemView.findViewById(R.id.material_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                if (viewType == leftSide) R.layout.left_side_category
                else R.layout.right_side_category, parent, false
            )
        return ViewHolder(view)
    }


    val leftSide = 10
    val rightSide = 20

    override fun getItemViewType(position: Int): Int {
        if (position%2==0) return leftSide
        else return rightSide
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = categoryList[position]
        holder.title.setText(item.title)
        holder.img.setImageResource(item.imageResourceID)
        holder.materialCard.
        setCardBackgroundColor(ContextCompat
            .getColor(holder.itemView.context, item.bg_color)
        )
        onItemClickListener?.let {
            holder.itemView.setOnClickListener {
                onItemClickListener?.onItemClick(position,item)

            }
        }
    }
     interface OnItemClickListener{
         fun onItemClick(pos:Int,category:CategoryModel)
     }
    var onItemClickListener:OnItemClickListener?=null

}
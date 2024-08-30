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

class CategoryAdapter (private val categoryList: List<CategoryModel>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    companion object{
        const val RIGHT_SIDE = 1
        const val LEFT_SIDE = 2
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val title : TextView = itemView.findViewById(R.id.title_card)
        val img : ImageView = itemView.findViewById(R.id.img_card)
        val materialCard : MaterialCardView = itemView.findViewById(R.id.material_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                if (viewType == LEFT_SIDE) R.layout.left_side_category
                else R.layout.right_side_category, parent, false
            )
        return ViewHolder(view)
    }


    override fun getItemViewType(position: Int): Int {
        return if (position%2==0) LEFT_SIDE
        else RIGHT_SIDE
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = categoryList[position]
        holder.title.text = item.title
        holder.img.setImageResource(item.imageResourceID)
        holder.materialCard.
        setCardBackgroundColor(ContextCompat
            .getColor(holder.itemView.context, item.backgroundColor)
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
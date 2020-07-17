package dev.swote.storeconnect.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.swote.storeconnect.R
import dev.swote.storeconnect.activitys.FoodListActivity
import dev.swote.storeconnect.activitys.MainActivity
import dev.swote.storeconnect.functions.ImageHelper
import dev.swote.storeconnect.functions.ImageHelper.foodList
import dev.swote.storeconnect.models.Food
import kotlinx.android.synthetic.main.item_food.view.*

class FoodRegisterAdapter(val context: Context, val foodList: ArrayList<Food>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val FOOTER = 1
        const val DATA = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            DATA -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
                FoodHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_footer, parent, false)
                FooterHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return foodList.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is FoodHolder -> {
                holder.setData(foodList[position])
            }
            else -> {
                holder.itemView.setOnClickListener {
                    context.startActivity(Intent(context, FoodListActivity::class.java))
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position <= foodList.size)
            DATA
        else
            FOOTER
    }

    inner class FoodHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameTextView :TextView = view.findViewById(R.id.name)
        private val priceTextView : TextView = view.findViewById(R.id.price)
        private val imageView : ImageView = view.findViewById(R.id.image)
        fun setData(data: Food) {
            nameTextView.text = data.name
            priceTextView.text = data.price.toString()
            imageView.image.setImageBitmap(ImageHelper.foodToName[data])
        }
    }

    inner class FooterHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}
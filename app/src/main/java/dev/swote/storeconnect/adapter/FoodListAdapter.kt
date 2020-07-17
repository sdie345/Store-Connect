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

class FoodListAdapter(val context: Context, val foodList : ArrayList<Food>) : RecyclerView.Adapter<FoodListAdapter.FoodHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return FoodHolder(view)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.setData(foodList[position])
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
}
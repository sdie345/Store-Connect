package dev.swote.storeconnect.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.swote.storeconnect.R
import dev.swote.storeconnect.functions.ImageHelper
import dev.swote.storeconnect.models.Food
import dev.swote.storeconnect.models.Order
import kotlinx.android.synthetic.main.item_food.view.*

class OrderListAdapter(val context: Context, val orderList : ArrayList<Order>) : RecyclerView.Adapter<OrderListAdapter.OrderHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return OrderHolder(view)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holder: OrderHolder, position: Int) {
        holder.setData(orderList[position])
    }

    inner class OrderHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameTextView : TextView = view.findViewById(R.id.name)
        private val priceTextView : TextView = view.findViewById(R.id.price)
        private val imageView : ImageView = view.findViewById(R.id.image)
        fun setData(data: Order) {
            nameTextView.text = data.title
            priceTextView.text = data.description.toString()
        }
    }
}
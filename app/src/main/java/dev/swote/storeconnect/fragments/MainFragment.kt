package dev.swote.storeconnect.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import dev.swote.storeconnect.R
import dev.swote.storeconnect.adapter.FoodListAdapter
import dev.swote.storeconnect.adapter.OrderListAdapter
import dev.swote.storeconnect.adapter.PagerAdapter

class MainFragment : Fragment() {
    lateinit var orderRecyclerView : RecyclerView
    lateinit var orderRecyclerAdapter : OrderListAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)
        orderRecyclerView = rootView.findViewById(R.id.order_recycler)
        orderRecyclerView.layoutManager = LinearLayoutManager(context)
        orderRecyclerView.adapter = orderRecyclerAdapter
        return rootView
    }
}
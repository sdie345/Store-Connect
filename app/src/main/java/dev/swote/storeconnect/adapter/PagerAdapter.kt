package dev.swote.storeconnect.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import dev.swote.storeconnect.fragments.ChattingFragment
import dev.swote.storeconnect.fragments.MainFragment
import dev.swote.storeconnect.fragments.ProfileFragment
import dev.swote.storeconnect.fragments.RegisterFragment

class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    companion object {
        const val FRAGMENT_COUNT = 4
    }
    override fun getItemCount(): Int {
        return FRAGMENT_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> RegisterFragment()
            2 -> ChattingFragment()
            3 -> ProfileFragment()
            else -> MainFragment()
        }
    }
}
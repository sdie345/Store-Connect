package dev.swote.storeconnect.activitys

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.swote.storeconnect.R
import dev.swote.storeconnect.adapter.PagerAdapter
import dev.swote.storeconnect.functions.ImageHelper
import dev.swote.storeconnect.models.Food

class MainActivity : AppCompatActivity() {

    lateinit var pager : ViewPager2
    lateinit var pagerAdapter : PagerAdapter
    lateinit var bottomNavigation : BottomNavigationView
    lateinit var imageHelper : ImageHelper
    var foodToImage : HashMap<Food, Image> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pager = findViewById(R.id.main_pager)
        bottomNavigation = findViewById(R.id.bottom_navigation)
        pagerAdapter = PagerAdapter(this@MainActivity)

        //pager setting
        pager.run {
            adapter = pagerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            currentItem = 4
            offscreenPageLimit = 2
            isUserInputEnabled = false
        }

        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_register -> pager.setCurrentItem(1, true)
                R.id.nav_chatting -> pager.setCurrentItem(2, true)
                R.id.nav_profile -> pager.setCurrentItem(3, true)
                else -> pager.setCurrentItem(0, true)
            }
            true
        }

        imageHelper.initFoodData(this)
        imageHelper.initImageData(this)
    }
}

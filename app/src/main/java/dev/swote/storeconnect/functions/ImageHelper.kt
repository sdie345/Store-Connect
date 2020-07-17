package dev.swote.storeconnect.functions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.swote.storeconnect.models.Food

object ImageHelper {
    val foodList : ArrayList<Food> = ArrayList()
    val foodToName : HashMap<Food, Bitmap> = HashMap()
    fun initFoodData(context: Context) {
        try {
            val gson: Gson = Gson()
            val bufferReader = context.assets.open("food_data_list.json").bufferedReader()
            val jsonString = bufferReader.use { it.readText() }

            foodList.clear()
            val type = object : TypeToken<ArrayList<Food>>(){}.type
            foodList.addAll(gson.fromJson(jsonString, type))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun initImageData(context: Context) {
        foodList.forEach {
            try {
                val inputStream = context.assets.open("img/${it.type}/${it.name}.jpg")
                val bitmap = BitmapFactory.decodeStream(inputStream)
                foodToName[it] = bitmap
            } catch (e : Exception) {
                e.printStackTrace()
            }
        }
    }
}
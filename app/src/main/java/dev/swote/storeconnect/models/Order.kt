package dev.swote.storeconnect.models

import com.google.gson.annotations.SerializedName

class Order(
    @SerializedName("title") val title: String,
    @SerializedName("place") val place: String,
    @SerializedName("description") val description: String,
    @SerializedName("food_list") val foodList: ArrayList<Food>,
    @SerializedName("total_price") val totalPrice: Int
)
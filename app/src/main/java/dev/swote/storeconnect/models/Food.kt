package dev.swote.storeconnect.models

import com.google.gson.annotations.SerializedName

class Food (
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: Int,
    @SerializedName("type") val type: String
)
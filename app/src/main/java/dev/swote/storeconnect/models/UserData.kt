package dev.swote.storeconnect.models

import com.google.gson.annotations.SerializedName

class UserData(
    @SerializedName("username") val userName: String,
    @SerializedName("manner_temperature") val mannerTemperature: Int
)
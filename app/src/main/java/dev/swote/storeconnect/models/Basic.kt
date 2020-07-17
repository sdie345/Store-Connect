package dev.swote.storeconnect.models

import com.google.gson.annotations.SerializedName

class Basic (
    @SerializedName("result") val result: Boolean,
    @SerializedName("data") val data: String
)
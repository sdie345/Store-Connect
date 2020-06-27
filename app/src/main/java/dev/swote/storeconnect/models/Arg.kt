package dev.swote.storeconnect.models

import com.google.gson.annotations.SerializedName

class Arg<T> (
    @SerializedName("result") val result: Boolean,
    @SerializedName("message") val msg: String,
    @SerializedName("data") val data: T
)
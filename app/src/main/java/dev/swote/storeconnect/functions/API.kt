package dev.swote.storeconnect.functions

import dev.swote.storeconnect.models.Arg
import dev.swote.storeconnect.models.Order
import dev.swote.storeconnect.models.UserData
import io.reactivex.Single
import okhttp3.Cookie
import retrofit2.Call
import retrofit2.http.*

interface API {

    @POST("/auth/login")
    @FormUrlEncoded
    fun logIn(@Field("email") id: String, @Field("password") pw: String): Call<Arg<String>>

    @POST("/auth/sign_up")
    @FormUrlEncoded
    fun signUp(@Field("email") id: String, @Field("password") pw: String): Call<Arg<String>>

    @POST("/getProfileData")
    @FormUrlEncoded
    fun getProfileData(@Header("Authorization") session: String?): Call<Arg<UserData>>

    @POST("getOrderList")
    @FormUrlEncoded
    fun getOrderList(@Header("Authorization") session: String?): Call<Arg<Order>>
}8
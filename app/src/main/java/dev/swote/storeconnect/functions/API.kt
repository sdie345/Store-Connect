package dev.swote.storeconnect.functions

import android.telephony.cdma.CdmaCellLocation
import dev.swote.storeconnect.models.*
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

    @POST("register")
    @FormUrlEncoded
    fun register(@Header("Authorization") session: String?, @Field("title") title: String, @Field("location") location: String, @Field("description") description: String, @Field("foodList") foodList: ArrayList<Food>)
    :  Call<Basic>
}
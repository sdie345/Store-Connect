package dev.swote.storeconnect.functions

import dev.swote.storeconnect.models.Arg
import io.reactivex.Single
import okhttp3.Cookie
import retrofit2.Call
import retrofit2.http.*

interface API {

    @POST("/auth/login")
    @FormUrlEncoded
    fun logIn(@Field("email") id : String, @Field("password") pw : String) :  Call<Arg<String>>

}
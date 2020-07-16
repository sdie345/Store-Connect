package dev.swote.storeconnect.functions

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


object SharedPref {
    private const val LOGIN_ID = "login.id"
    private const val LOGIN_SESSION = "login.session"

    private var sharedPref: SharedPreferences? = null
    private lateinit var gson : Gson
    private lateinit var favoriteListType: Type
    private lateinit var stationIdMapType: Type
    fun openSharedPrep(context: Context) {
        this.sharedPref = context.getSharedPreferences(LOGIN_SESSION, Context.MODE_PRIVATE)
        this.sharedPref = context.getSharedPreferences(LOGIN_ID, Context.MODE_PRIVATE)
        gson = GsonBuilder().create()
    }
    // SharedPref 에 login session 을 작성
    fun writeLoginSession(data: String?) {
        if(this.sharedPref == null) {
            Log.e("SharedPref", "didn't start openSharedPrep()")
        } else {
            sharedPref?.edit()?.putString("session", data)?.apply()
        }
    }
    // sharedPref 의 login session 을 가져옴
    fun readLoginSession() : String? {
        return if(this.sharedPref == null) {
            Log.e("SharedPref", "didn't start openSharedPrep()")
            null
        } else sharedPref?.getString("session", null)
    }

    fun writeId(data : String) {
        if(this.sharedPref == null) {
            Log.e("SharedPref", "didn't start openSharedPrep()")
        } else {
            sharedPref?.edit()?.putString("id", data)?.apply()
        }
    }

    fun readId() : String? {
        return if(this.sharedPref == null) {
            Log.d("SharedPref", "didn't start openSharedPrep()")
            null
        } else sharedPref?.getString("id", null)
    }
}
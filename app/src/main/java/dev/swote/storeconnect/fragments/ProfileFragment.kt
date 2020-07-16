package dev.swote.storeconnect.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.shadow.ShadowRenderer
import dev.swote.storeconnect.R
import dev.swote.storeconnect.activitys.OrderListActivity
import dev.swote.storeconnect.functions.Client
import dev.swote.storeconnect.functions.SharedPref
import dev.swote.storeconnect.models.Arg
import dev.swote.storeconnect.models.UserData
import retrofit2.Call
import retrofit2.Response

class ProfileFragment : Fragment() {
    lateinit var orderListLayout: LinearLayout
    lateinit var progressBar: ProgressBar
    lateinit var progressBarText: TextView
    lateinit var userName: TextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        context?.let { SharedPref.openSharedPrep(it) }
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)

        orderListLayout = rootView.findViewById(R.id.order_list)
        userName = rootView.findViewById(R.id.user_name)
        progressBar = rootView.findViewById(R.id.manner_progressbar)
        progressBarText = rootView.findViewById(R.id.manner_text)
        Client.retrofitService.getProfileData(SharedPref.readLoginSession()).enqueue(object : retrofit2.Callback<Arg<UserData>> {
            override fun onFailure(call: Call<Arg<UserData>>, t: Throwable) {
                Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show()
            }
 
            override fun onResponse(call: Call<Arg<UserData>>, response: Response<Arg<UserData>>) {
                val res = response.body()
                if (res != null) {
                    when(res.result) {
                        true -> {
                            userName.text = res.data.userName
                            progressBar.progress = res.data.mannerTemperature
                            val string = res.data.mannerTemperature.toString() + "℃"
                            progressBarText.text = string
                        }
                        else -> {
                            Toast.makeText(context, "불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else {
                    Toast.makeText(context, "ERROR : ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }
        })


        orderListLayout.setOnClickListener {
            startActivity(Intent(activity, OrderListActivity::class.java))
        }
        return rootView
    }
}
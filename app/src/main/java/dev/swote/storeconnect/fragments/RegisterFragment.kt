package dev.swote.storeconnect.fragments

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import dev.swote.storeconnect.R
import dev.swote.storeconnect.adapter.FoodRegisterAdapter
import dev.swote.storeconnect.functions.Client
import dev.swote.storeconnect.functions.SharedPref
import dev.swote.storeconnect.models.Basic
import dev.swote.storeconnect.models.Food
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class RegisterFragment : Fragment() {
    val foodList :ArrayList<Food> = ArrayList()
    lateinit var titleEditTextView : TextInputEditText
    lateinit var locationEditTextView : TextInputEditText
    lateinit var descriptionEditTextView : TextInputEditText
    lateinit var registerButton : Button
    lateinit var cancelButton : Button
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerVIewAdapter: FoodRegisterAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView = inflater.inflate(R.layout.fragment_register, container, false)
        titleEditTextView = rootView.findViewById(R.id.title_edit_text)
        locationEditTextView = rootView.findViewById(R.id.location_edit_text)
        descriptionEditTextView = rootView.findViewById(R.id.description_edit_text)
        registerButton = rootView.findViewById(R.id.register_button)
        cancelButton = rootView.findViewById(R.id.cancel_button)
        recyclerView = rootView.findViewById(R.id.register_recycler)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerVIewAdapter = FoodRegisterAdapter(context!!, foodList)
        recyclerView.adapter = recyclerVIewAdapter

        context?.let { SharedPref.openSharedPrep(it) }


        cancelButton.setOnClickListener {
            titleEditTextView.text?.clear()
            locationEditTextView.text?.clear()
            descriptionEditTextView.text?.clear()
            foodList.clear()
            recyclerVIewAdapter.notifyDataSetChanged()
        }

        registerButton.setOnClickListener {
            Client.retrofitService.register(SharedPref.readLoginSession(), titleEditTextView.text.toString(),
            locationEditTextView.text.toString(), descriptionEditTextView.text.toString(), foodList).enqueue(object : retrofit2.Callback<Basic> {
                override fun onFailure(call: Call<Basic>, t: Throwable) {
                    Toast.makeText(context, "Server Error!", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Basic>, response: Response<Basic>) {
                    val res = response.body()
                    if (res != null) {
                        if (res.result) {
                            Toast.makeText(context, "등록 성공!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "등록에 실패했습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                    cancelButton.performClick()
                }
            })
        }

        return rootView
    }
}
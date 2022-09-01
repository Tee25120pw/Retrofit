package com.example.retrofit

import android.content.Context
import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class DataViewModel  : ViewModel() {
    val Base_Url = "https://jsonplaceholder.typicode.com/"
    lateinit var myAdapter: MyAdapter
    fun getMyData(context:Context,rcv: RecyclerView) {

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Base_Url)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(call: Call<List<MyDataItem>?>, response: Response<List<MyDataItem>?>) {
                val responseBody = response.body()!!
                myAdapter = MyAdapter(context, responseBody)
                myAdapter.notifyDataSetChanged()
                rcv.adapter = myAdapter
            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                d("ViewModel", "onFailure: " + t.message)
            }
        })
    }
}
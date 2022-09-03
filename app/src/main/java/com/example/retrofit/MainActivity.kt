package com.example.retrofit;

import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Array.get


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycleview_users.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recycleview_users.layoutManager = linearLayoutManager

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.getMyData(baseContext, recycleview_users)
    }

//private fun getMyData() {
//    val retrofitBuilder = Retrofit.Builder()
//        .addConverterFactory(GsonConverterFactory.create())
//        .baseUrl(Base_Url)
//        .build()
//        .create(ApiInterface::class.java)
//
//    val retrofitData = retrofitBuilder.getData()
//
//    retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
//        override fun onResponse(
//            call: Call<List<MyDataItem>?>,
//            response: Response<List<MyDataItem>?>
//        ) {
//            val responseBody = response.body()!!
//
//            var myAdapter = MyAdapter(baseContext, responseBody)
//            myAdapter.notifyDataSetChanged()
//            recycleview_users.adapter = myAdapter
//        }
//
//        override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
//            d("MainActivity", "onFailure: " + t.message)
//        }
//    })
//}
}
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.retrofit.ApiInterface
import com.example.retrofit.MyAdapter
import com.example.retrofit.MyDataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val Base_Url = "https://jsonplaceholder.typicode.com/"
open class ViewModel : ViewModel() {

//    lateinit var linearLayoutManager: LinearLayoutManager

    fun getMyData() {
        lateinit var myAdapter: MyAdapter

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Base_Url)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()


        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>
            ) {
                val responseBody = response.body()!!

//                    myAdapter = MyAdapter(baseContext, responseBody)
//                    myAdapter.notifyDataSetChanged()
//                    recycleview_users.adapter = myAdapter
            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
            }
        })
    }

}
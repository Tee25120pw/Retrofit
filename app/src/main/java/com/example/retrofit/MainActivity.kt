import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.ApiInterface
import com.example.retrofit.MyAdapter
import com.example.retrofit.MyDataItem
import com.example.retrofit.R
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val Base_Url = "https://jsonplaceholder.typicode.com/"
lateinit var linearLayoutManager: LinearLayoutManager


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycleview_users.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recycleview_users.layoutManager = linearLayoutManager

        getMyData()
    }

private fun getMyData() {
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

            var myAdapter = MyAdapter(baseContext, responseBody)
            myAdapter.notifyDataSetChanged()
            recycleview_users.adapter = myAdapter
        }

        override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
            d("MainActivity", "onFailure: " + t.message)
        }
    })
}}
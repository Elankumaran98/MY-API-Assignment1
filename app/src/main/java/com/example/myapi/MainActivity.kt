package com.example.myapi

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var rf=Retrofit.Builder()
            .baseUrl(RetrofitInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        var API=rf.create(RetrofitInterface::class.java)
        var call=API.posts

        call?.enqueue(object : Callback<List<postModel?>?> {
            override fun onResponse(
                call: Call<List<postModel?>?>?,
                response: Response<List<postModel?>?>?
            ) {
                var postlist: List<postModel>? = response?.body() as List<postModel>
                var post = arrayOfNulls<String>(postlist!!.size)

                for (i in postlist.indices)
                    post[i] = postlist[i].title

                for(j in postlist.indices)
                    post[j] = postlist[j].body

                var adapter = ArrayAdapter<String>(
                    applicationContext,
                    android.R.layout.simple_dropdown_item_1line,
                    post
                )
                ListView.adapter=adapter
            }

            override fun onFailure(call: Call<List<postModel?>?>?, t: Throwable?) {

            }

        })
    }
}
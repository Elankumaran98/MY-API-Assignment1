package com.example.myapi

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {
    @get:GET("posts")
    val posts: Call<List<postModel?>?>?
    companion object{
        const val BASE_URL="http://jsonplaceholder.typicode.com";
    }

}
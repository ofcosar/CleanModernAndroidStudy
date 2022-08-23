package com.example.cleanmodernandroidstudy.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        private var instance : Retrofit? = null
        private val BASE_URL = "https://foodish-api.herokuapp.com/"
       fun getImageService() : GetImageService{
           if (instance == null){
               instance = Retrofit
                   .Builder()
                   .baseUrl(BASE_URL)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build()
           }
           return instance!!.create(GetImageService::class.java)
       }
    }
}
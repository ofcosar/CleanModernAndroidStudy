package com.example.cleanmodernandroidstudy.service

import retrofit2.http.GET
import retrofit2.http.Path

interface GetImageService {
    @GET("api/images/{foodName}")
    suspend fun getImage(@Path("foodName") food : String)
}
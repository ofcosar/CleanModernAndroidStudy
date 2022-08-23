package com.example.cleanmodernandroidstudy.service

import com.example.cleanmodernandroidstudy.model.ImageModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GetImageService {
    @GET("api/images/{foodName}")
    suspend fun getImage(@Path("foodName") food : String) : Response<ImageModel>
}
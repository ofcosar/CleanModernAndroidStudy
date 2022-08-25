package com.example.cleanmodernandroidstudy

import com.example.cleanmodernandroidstudy.service.GetImageService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object appModule {
    @Singleton
    @Provides
    fun provideGetImageService() : GetImageService{
        val baseUrl = "https://foodish-api.herokuapp.com/"
            val retrofitInstance = Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofitInstance.create(GetImageService::class.java)
    }
}